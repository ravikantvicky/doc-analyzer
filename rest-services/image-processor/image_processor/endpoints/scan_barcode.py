import os
import sys
from flask import json, make_response, request

from image_processor import __version__, app, logger, config
from image_processor.models import image_aligner
from image_processor.utils import image_utils


@app.route('/api/scan-barcode', methods=['POST'])
def scan_barcode():
    '''
        Scan barcode from uploaded image
    '''
    logger.info("Processing request for /api/scan-barcode endpoint")
    status = config.HTTP_STATUS_OK

    try:
        message = do_post(request)
    except Exception as ex:
        logger.exception("Something went wrong !")
        message = {"error": str(ex)}
        status = config.HTTP_STATUS_ERROR

    resp = make_response(json.dumps(message), status)
    resp.headers['Content-Type'] = 'application/json'
    return resp


def do_post(request):
    '''
        Process the uploaded images and align the raw image as per template
    '''
    # step 1 : Check for JSON Payload
    content_type = request.headers['Content-Type'].split(";")[0]
    if(content_type != 'application/json'):
        logger.info(request.headers['Content-Type'])
        raise Exception("Content Type should be json")

    # Get POST parameters
    input_json = request.json
    image1_text = input_json["image"]
    img = image_utils.read_b64(image1_text)
    if img is None:
        raise Exception("Could not read raw image")
    logger.info("Uploaded Image shape : %s", str(img.shape))

    code = -1
    
    return {"code": code}
