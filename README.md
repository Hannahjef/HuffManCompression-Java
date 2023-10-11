
# Huffman Coding Implementation

## Table of Contents

- [Introduction](#introduction)
- [Project Description](#project-description)
- [Implementation](#implementation)
- [HuffmanTree Class](#huffmantree-class)
- [HuffmanAlgorithm Class](#huffmanalgorithm-class)
- [Building and Running](#building-and-running)


## Introduction

This C++ program implements the Huffman coding algorithm. Huffman coding is a lossless data compression algorithm that assigns variable-length codes to input characters based on their frequencies. This efficient compression technique is widely used in various applications, including data compression and encoding.

## Project Description

The project consists of two main classes: `HuffmanTree` and `HuffmanAlgorithm`.

### Implementation

### HuffmanTree Class

The `HuffmanTree` class represents the structure of Huffman trees and provides essential operations:

- **Constructors**: Create a Huffman tree with the given data.
- **Copy Constructor**: Make a copy of an existing Huffman tree.
- **Destructor**: Release memory when a tree is no longer needed.
- **Assignment Operator**: Assign one Huffman tree to another.
- **Comparison Operator**: Compare two Huffman trees based on their weights.
- **Code Generation Method**: Traverse the tree to generate Huffman codes for each character.

### HuffmanAlgorithm Class

The `HuffmanAlgorithm` class utilizes the `HuffmanTree` class to implement the Huffman coding algorithm. It includes the following methods:

- **Constructor**: Takes the frequency of each letter and constructs the Huffman tree.
- **Code Generation Method**: Generates Huffman codes for each character.
- **Word Encoding Method**: Encodes a given word using Huffman codes.
- **Output Operator**: Displays the letter-to-code translation table.

## Building and Running

To compile and run the program, follow these steps:

1. Ensure you have a C++ compiler (e.g., g++) installed on your system.
2. Compile the source files: `g++ HuffmanTree.cpp HuffmanAlgorithm.cpp main.cpp -o HuffmanCoding`
3. Run the executable: `./HuffmanCoding`




