import os
import sys
from flask import json, make_response, request

from image_processor import __version__, app, logger, config
from image_processor.models import image_aligner
from image_processor.utils import image_utils


@app.route('/api/align', methods=['POST'])
def align_endpoint():
    '''
        Align image to a given template
    '''
    logger.info("Processing request for /api/align endpoint")
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
    image1_text = input_json["raw_image"]
    raw_img = image_utils.read_b64(image1_text)
    if raw_img is None:
        raise Exception("Could not read raw image")
    logger.info("Raw Image shape : %s", str(raw_img.shape))

    image2_text = input_json["ref_image"]
    ref_img = image_utils.read_b64(image2_text)
    if ref_img is None:
        raise Exception("Could not read ref image")
    logger.info("Ref Image shape : %s", str(ref_img.shape))

    aligned_img, key_point_match_img = image_aligner.align_image(
        raw_img, ref_img)

    logger.info("Aligned Image shape : %s", str(aligned_img.shape))

    return {"aligned_img": image_utils.image_to_b64(aligned_img),
            "key_point_match_img": image_utils.image_to_b64(key_point_match_img)}
