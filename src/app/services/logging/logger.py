import os
import logging

def initializeLogger(name:str):
    # logger
    logger = logging.getLogger(name)
    if not logger.hasHandlers():
        logLevel = os.getenv('LOG_LEVEL', 'INFO')
        if logLevel == 'DEBUG':
            logLevel = logging.DEBUG
        elif logLevel == 'INFO':
            logLevel = logging.INFO
        elif logLevel == 'WARNING':
            logLevel = logging.WARNING
        elif logLevel == 'ERROR':
            logLevel = logging.ERROR
        else:
            logLevel = logging.INFO
        
        logger.setLevel(logLevel)
        logHandler = logging.StreamHandler()
        logHandler.setLevel(logLevel)
        logHandler.setFormatter(logging.Formatter('%(asctime)s - %(name)-70s - %(levelname)s - %(threadName)s - %(message)s'))
        logger.addHandler(logHandler)

        # if logs folder does not exist, create it
        logsBasePath = os.getenv('LOGS_BASE_PATH', 'logs')
        logFilePath = os.path.join(logsBasePath, 'report.log')
        if not os.path.exists(logsBasePath):
            os.makedirs(logsBasePath)
        logHandler = logging.FileHandler(logFilePath)
        logHandler.setLevel(logLevel)
        logHandler.setFormatter(logging.Formatter('%(asctime)s - %(name)-70s - %(levelname)-7s - %(threadName)s - %(message)s'))
        logger.addHandler(logHandler)

        logger.propagate = False
    return logger