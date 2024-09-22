from dotenv import load_dotenv
from pydantic_settings import BaseSettings

load_dotenv()

class Settings(BaseSettings):
    OPENAI_API_KEY: str
    ELEVEN_LABS_API_KEY : str

settings = Settings()