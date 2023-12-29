from loguru import logger

class CommunicationData():

    def __init__(self, key: str=None, check_id: str=None):
        self.__key: str = key
        self.__check_id:str = check_id

    
    def get_key(self) -> str:
        return self.__key
    
    def get_check_id(self) -> str:
        return self.__check_id
    
    def set_key(self, key):
        if (isinstance(key, str)):
            self.__key = key
        else:
            logger.warning("The key must be a string")
    
    def set_check_id(self, check_id):
        if (isinstance(check_id, str)):
            self.__check_id = check_id
        else:
            logger.warning("The key must be a string")

    def __str__(self):
        return str(self.__check_id)
    
    def __str__(self):
        return str(self.__key)


    def insert_data_from_keyword(self):
        key = input("Enter the Key: ")
        self.set_key(key)
        check_id = input("Enter the Check ID: ")
        while (len(check_id) != 16):
            check_id = input("Inválid length for the Id of Check: Enter the Check ID with 16 digits: ")
        
        self.set_check_id(check_id)


