from fastapi import Depends, FastAPI
from fastapi.responses import StreamingResponse
from common.settings import settings
from services.interview import chat

app = FastAPI()


@app.get("/")
async def root():
    return {"message": f"Health check succeeded {settings.OPENAI_API_KEY}"}

@app.get("/talk")
async def talk(text: str):
    audio_stream = await chat(text)
    return StreamingResponse(audio_stream, media_type="audio/mpeg")