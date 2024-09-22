from common.openAI import readPrompt, getModel
from .models import InterviewState, PhaseModel, Phase, Dialogue, expectedApproach


def controlNode(state: InterviewState):
    prompt = readPrompt("services/workflows/interview_assistant/prompts/control/classifyConversation.txt")
    model = getModel()
    chain = prompt | model.with_structured_output(PhaseModel)
    response = chain.invoke({'conversations': state.conversations})
    state.phase = Phase(response.phase)
    return state.dict()


def introductionNode(state: InterviewState):
    prompt = readPrompt("services/workflows/interview_assistant/prompts/introduction/introduction.txt")
    model = getModel()

    chain = prompt | model.with_structured_output(Dialogue, method="json_schema")

    dialogue = chain.invoke({'conversations': state.conversations})
    state.conversations.append(dialogue)

    return state.dict()


def describeQuestionNode(state: InterviewState):
    prompt = readPrompt("services/workflows/interview_assistant/prompts/questions/describeQuestion.txt")
    model = getModel()

    chain = prompt | model.with_structured_output(Dialogue, method="json_schema")

    dialogue = chain.invoke({'conversations': state.conversations, 'question': state.question})

    state.conversations.append(dialogue)

    return state.dict()


def describeApproachNode(state: InterviewState):
    prompt = readPrompt("services/workflows/interview_assistant/prompts/questions/explainApproach.txt")
    model = getModel()

    chain = prompt | model.with_structured_output(Dialogue, method="json_schema")

    dialogue = chain.invoke(
        {'conversations': state.conversations, 'question': state.question, 'expectedApproach': expectedApproach})

    state.conversations.append(dialogue)

    return state.dict()