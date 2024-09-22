from io import BytesIO

from elevenlabs import VoiceSettings
from elevenlabs.client import ElevenLabs
from common.settings import settings


async def convertTextToAudio(text):
    client = ElevenLabs(
        api_key=settings.ELEVEN_LABS_API_KEY,
    )
    response  = client.text_to_speech.convert(
        voice_id="cjVigY5qzO86Huf0OWal",
        optimize_streaming_latency="0",
        output_format="mp3_22050_32",
        text=text,
        voice_settings=VoiceSettings(
            stability=0.1,
            similarity_boost=0.3,
            style=0.2,
        ),
    )
    audio_stream = BytesIO()
    for chunk in response:
        if chunk:
            audio_stream.write(chunk)
    audio_stream.seek(0)
    return audio_stream

