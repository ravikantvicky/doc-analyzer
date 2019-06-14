import cv2
import numpy as np
import base64

def read_b64(text):
    '''
    Convert base64 encoded image to OpenCV Imge format
    
    Args:
        text (str): base64 encoded image
    
    Returns:
        [img]: OpenCV Image
    '''
    text = str(text)
    if ',' in text:
        encoded_data = text.split(',')[1]
    else:
        encoded_data = text

    np_arr = np.fromstring(base64.b64decode(encoded_data), np.uint8)
    img = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)
    return img

def read_from_file(file_path):
    '''
    Read Image from File
    '''
    return cv2.imread(file_path)

def image_to_b64(img):
    '''
    Convert Image to Base64 Format
    '''
    _, buffer = cv2.imencode(".jpg", img)
    # _, buffer = cv2.imencode(".jpg", cv2.cvtColor(img, cv2.COLOR_RGB2BGR))
    img_as_text = base64.b64encode(buffer)
    return img_as_text.decode("utf-8")