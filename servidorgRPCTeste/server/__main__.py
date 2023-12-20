from loguru import logger
from server_euromil.server import run_server

def main():
    
    logger.info("Starting the server . . .")
    run_server()


if __name__ == '__main__':
    main()