# Defining the interview state object

question = """
    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    You can return the answer in any order.

    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
"""

expectedCode = """class Solution:
        def twoSum(self, nums: List[int], target: int) -> List[int]:
            hashmap = {}
            for i in range(len(nums)):
                hashmap[nums[i]] = i
            for i in range(len(nums)):
                complement = target - nums[i]
                if complement in hashmap and hashmap[complement] != i:
                    return [i, hashmap[complement]]

        """
expectedApproach = "A simple implementation uses two iterations. In the first iteration, we add each element's value as a key and its index as a value to the hash table. Then, in the second iteration, we check if each element's complement (target−nums[i]) exists in the hash table. If it does exist, we return current element's index and its complement's index. Beware that the complement must not be nums[i] itself!"

from pydantic import BaseModel, Field
from typing import List, Literal
from enum import Enum


class Phase(str, Enum):
    INTRODUCTION = 'introduction'
    QUESTION = 'question'
    APPROACH = 'approach'


class PhaseModel(BaseModel):
    phase: Literal['introduction', 'question', 'approach']


class Dialogue(BaseModel):
    dialogue: str = Field(description="The dialogue in plain string")
    speaker: Literal['interviewer', 'candidate'] = Field(description="The speaker of the dialogue")


class InterviewState(BaseModel):
    conversations: List[Dialogue] = Field(default_factory=list)
    question: str = question
    expectedApproach: str = expectedApproach
    expectedCode: str = expectedCode

    userCode: str = ""
    phase: Phase = Phase.INTRODUCTION


class IntroductionResponse(BaseModel):
    dialogue: str
