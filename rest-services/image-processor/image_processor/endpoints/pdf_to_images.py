
import os
import tempfile

import cv2
import numpy as np
import pdf2image

from flask import request, make_response, json
from image_processor import app, logger, config
from image_processor.utils import image_utils

'''
    Convert PDF to Images
'''

@app.route('/api/pdf-to-image', methods=['post'])
def pdf_to_image():
    logger.info("Processing POST request for /api/pdf-to-image ")
    status = config.HTTP_STATUS_OK
    try:
        file_list = list(request.files.keys())
        num_files = len(file_list)
        logger.info(file_list)
        if num_files == 0:
            raise Exception(
                "Needs 1 pdf file but received {} files".format(num_files))
        elif len(file_list) > 1:
            err_msg = "More than 1 file uploaded. Got {} files".format(num_files)
            raise Exception(err_msg)

        uploaded_file = request.files[file_list[0]]
        uploaded_file_name = uploaded_file.filename 
        if uploaded_file_name == "":
            raise Exception("Empty file uploaded")
        elif os.path.splitext(uploaded_file_name)[-1] not in (".pdf", ".PDF"):
            raise Exception("Please upload a pdf file only") 

        # Read pdf file
        pages = pdf2image.convert_from_bytes(uploaded_file.read())
        images = []
        for page in pages:
            img = cv2.cvtColor(np.array(page), cv2.COLOR_RGB2BGR)
            images.append(image_utils.image_to_b64(img))

        result={"images": images}

    except Exception as ex:
        logger.exception("Something went wrong")
        result = {"error" : str(ex)}
        status = config.HTTP_STATUS_ERROR

    resp = make_response(json.dumps(result), status )
    resp.headers['Content-Type'] = 'application/json'

    return resp
