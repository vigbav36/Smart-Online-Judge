from typing import Literal

from langgraph.constants import START
from langgraph.graph import END, StateGraph
from .models import InterviewState, Phase
from .nodes import controlNode, introductionNode, describeQuestionNode, describeApproachNode


def checkPhase(state: InterviewState) -> Literal["introductionNode", "describeQuestionNode", "describeApproachNode", END]:
    if state.phase == Phase.INTRODUCTION:
        return "introductionNode"
    if state.phase == Phase.QUESTION:
        return "describeQuestionNode"
    if state.phase == Phase.APPROACH:
        return "describeApproachNode"

    return END



def initializeGraph():
    workflow = StateGraph(InterviewState)

    workflow.add_node("controlNode", controlNode)
    workflow.add_node("introductionNode", introductionNode)
    workflow.add_node("describeQuestionNode", describeQuestionNode)
    workflow.add_node("describeApproachNode", describeApproachNode)

    workflow.add_edge(START, "controlNode")
    workflow.add_conditional_edges(
        "controlNode",
        checkPhase,
    )
    graph = workflow.compile()
    return graph