# Server Dispatching Simulation

A comprehensive Java simulation that models server farm job distribution using multiple dispatching algorithms to optimize server utilization and minimize job waiting times.

## Overview

This project implements a server farm simulation to study the effectiveness of various job assignment strategies. The simulation demonstrates how different dispatching algorithms affect server performance and job waiting times, providing insights into optimal server farm configuration.

## Key Features

- **Multiple Dispatching Algorithms**: Random, Round-Robin, Least Work, and Shortest Queue dispatchers
- **Queue Implementation**: Custom linked-list based queue data structure
- **Performance Analysis**: Comprehensive testing with configurable parameters (server count, job volume, processing times)
- **Mathematical Modeling**: Theoretical analysis of optimal server requirements
- **Visualization**: Real-time server farm visualization (ServerFarmViz)

## Algorithms Implemented

### Job Dispatchers
1. **Random Dispatcher**: Assigns jobs to randomly selected servers
2. **Round-Robin Dispatcher**: Distributes jobs in circular fashion across servers
3. **Least Work Dispatcher**: Assigns jobs to servers with minimum remaining processing time
4. **Shortest Queue Dispatcher**: Routes jobs to servers with fewest queued jobs

### Core Components
- **Job Class**: Represents individual processing tasks with arrival and processing times
- **Server Class**: Models server behavior with job queues and processing capabilities
- **JobMaker**: Generates jobs with exponential distribution for realistic simulation
- **LinkedList**: Custom implementation with iterator support for queue operations

## Performance Results

Simulation with 34 servers and 10 million jobs revealed:

| Dispatcher Type | Average Waiting Time |
|----------------|---------------------|
| Shortest Queue | ~100ms (optimal) |
| Least Work | ~100ms (near-optimal) |
| Round-Robin | Higher than optimal |
| Random | Worst performance |

## Mathematical Analysis

The project includes theoretical analysis showing that the optimal number of servers follows:

```
Optimal Servers = Processing Time / Arrival Time
```

For the test case (100ms processing, 3ms arrival): 100/3 ≈ 33.3, requiring 34 servers for optimal performance.



## Key Findings

1. **Shortest Queue Dispatcher** provides optimal performance by minimizing server idle time
2. **Server count optimization** follows predictable mathematical relationships
3. **Load balancing** significantly impacts overall system performance
4. **Theoretical models** closely match empirical simulation results

## Technical Implementation

- **Object-Oriented Design**: Abstract JobDispatcher class with concrete implementations
- **Data Structures**: Custom linked-list implementation for efficient queue operations
- **Simulation Engine**: Event-driven processing with configurable parameters
- **Performance Metrics**: Comprehensive analysis of waiting times and server utilization

## Running the Simulation

The simulation can be configured with various parameters:
- Number of servers
- Job volume (tested up to 10 million jobs)
- Processing time distribution
- Job arrival patterns

## Author

**Aayan Shah**  
Computer Science & Physics Student  
[GitHub Profile](https://github.com/aayans314)
