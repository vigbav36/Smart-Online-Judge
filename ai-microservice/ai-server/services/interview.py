from openai import audio

from .audio import convertTextToAudio
from .workflows.interview_assistant.graph import initializeGraph
from .workflows.interview_assistant.models import InterviewState, Dialogue

memory : dict[str, InterviewState]= {'1' : InterviewState()}
graph = initializeGraph()

async def process_user_input(user_input):
    try:
        state : InterviewState = memory['1']
        state.conversations.append(Dialogue(dialogue=user_input, speaker="candidate"))
        finalState = graph.invoke(state)
        memory['1'] = InterviewState(**finalState)
        return memory['1']
    except Exception as e:
        print(e)


async def chat(userText):
    user_input = userText
    response = await process_user_input(user_input)
    text = response.conversations[len(response.conversations) - 1].dialogue
    audio_stream = await convertTextToAudio(text)
    return audio_stream