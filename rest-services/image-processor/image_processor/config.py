
import os
from os.path import join, abspath, exists
from flask import json


class Config():
    '''
        Define all global constants using the CONFIG.json file
    '''

    def __init__(self):
        # Open and read the config file
        CONFIG_FILE = "./CONFIG.json"
        with open(CONFIG_FILE, "r") as file_object:
            config_json = json.load(file_object)

        '''
            Load all global constants from configuration JSON file.
        '''
        # Server Details
        self.HOST = config_json["HOST"]
        self.PORT = config_json["PORT"]
        self.ENABLE_CORS = config_json["ENABLE_CORS"]

        # Logger Configs
        self.LOG_DIR = abspath(config_json["LOG_DIR"])
        self.LOG_FORMAT = config_json["LOG_FORMAT"]
        self.LOG_DATE_FORMAT = config_json["LOG_DATE_FORMAT"]
        self.LOG_LEVEL = config_json["LOG_LEVEL"]

        self.HTTP_STATUS_OK = config_json["HTTP_STATUS_OK"]
        self.HTTP_STATUS_CREATED = config_json["HTTP_STATUS_CREATED"]
        self.HTTP_STATUS_ACCEPTED = config_json["HTTP_STATUS_ACCEPTED"]
        self.HTTP_STATUS_UNAUTHORIZED = config_json["HTTP_STATUS_UNAUTHORIZED"]
        self.HTTP_STATUS_NOT_FOUND = config_json["HTTP_STATUS_NOT_FOUND"]
        self.HTTP_STATUS_ERROR = config_json["HTTP_STATUS_ERROR"]

        self.DATA_DIR = config_json["DATA_DIR"]
        self.STATIC_DIR = config_json["STATIC_DIR"]
