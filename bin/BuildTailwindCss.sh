#!/bin/bash

# Set env
INPUT_CSS_PATH="./src/main/resources/static/css/input.css"
OUTPUT_CSS_PATH="./src/main/resources/static/css/tailwind.css"

# Run
npx @tailwindcss/cli -i $INPUT_CSS_PATH -o $OUTPUT_CSS_PATH
