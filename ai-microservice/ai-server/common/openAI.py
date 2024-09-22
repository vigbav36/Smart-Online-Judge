from langchain_core.prompts import PromptTemplate
from langchain_openai import ChatOpenAI
from common.settings import settings


def readPrompt(filePath):
    content = open(filePath).read()
    return PromptTemplate.from_template(content)

def getModel():
    return  ChatOpenAI(
        model="gpt-4o-mini",
        temperature=0,
        api_key=settings.OPENAI_API_KEY,
    )