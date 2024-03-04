# Town Prosperity Optimization with AI Agent

## Overview

This repository contains the implementation of an AI agent designed to solve a town prosperity optimization problem. The goal is to manage the resources of a town effectively to elevate its prosperity level to 100. The problem involves various actions such as requesting resource deliveries, waiting for pending deliveries, and building new structures, each associated with costs, resource requirements, and delays. The agent must operate within a limited budget, efficiently manage resource levels, and address temporal aspects introduced by delayed resource deliveries.

## Problem Description

The town's prosperity is measured numerically, and the challenge is to find a plan that optimizes the use of resources and minimizes expenses. The available actions, constrained budgets, and limited resource storage capacity add complexity to the problem. The agent must explore different action sequences, considering costs and constraints, to devise a plan that achieves the target prosperity level of 100.

## Features

- **Search Algorithms:** The agent utilizes various search algorithms, including Depth-First Search (DFS), Breadth-First Search (BFS), heuristic search, and A* to efficiently explore the solution space.
  
- **Prolog Implementation:** Additionally, a Prolog implementation using a knowledge base and predicate logic is provided. This implementation allows for a logical representation of the problem domain and facilitates intelligent reasoning.

## Usage

To use the AI agent:

1. Clone the repository.
2. Choose the appropriate search algorithm or Prolog implementation based on your preferences or requirements.
3. Run the agent with the desired input parameters, considering the town's budget, resource constraints, and other relevant factors.

## Folder Structure

- **/src:** Contains source code for the AI agent.
- **/prolog:** Includes Prolog implementation files for knowledge base and predicate logic.

## Contributing

Contributions are welcome! Feel free to enhance existing algorithms, improve the Prolog implementation, or suggest new features. Please follow the contribution guidelines in the `CONTRIBUTING.md` file.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use and modify the code as needed, adhering to the license terms.

## Acknowledgments
