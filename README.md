# Demultiplexer

## Installation

Use git to clone project for usage.
```bash 
git clone git@github.com:Muniru/Demultiplexer.git
```

## Usage

### Parameters

| Parameter         | Type       | Description                                                                         | Required | Default Value     |
|-------------------|------------|-------------------------------------------------------------------------------------|----------|-------------------|
| `-f`, `--fastq`   | `.fastq`   | Fastq file to be multiplexed                                                        | Yes      | N/A               |
| `-i`, `--indexes` | `.csv`     | Comma seperated file including the sequencing barcodes and indices for all samples. | Yes      | N/A               |
| `-e`, `--error`   | `Inrerger` | Maximum allowed mismatches.                                                         | Yes      | N/A               |
| `-o`, `--output`  | `Path`     | Output directory.                                                                   | Yes      | N/A               |
| `-t`, `--thread`  | `Interger` | Number of threads used.                                                             | No       | Maximum threads   |


### Bash

To run the program run as shown below.

#### Example
```bash
java -jar ~Demultiplexer/build/libs/Demultiplexer-1.0-SNAPSHOT.jar  -f [File] -i [File] -e [Interger]
```

