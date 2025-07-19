#!/bin/bash

find . -name "*ServiceImp.java" | while read file; do
    echo "Cleaning $file"

    # Supprimer le paramètre "CacheNameProvider cacheNameProvider" (avec ou sans virgule)
    sed -i 's/, *CacheNameProvider *[a-zA-Z0-9_]*//g' "$file"
    sed -i 's/CacheNameProvider *[a-zA-Z0-9_]*, *//g' "$file"
    sed -i 's/CacheNameProvider *[a-zA-Z0-9_]*//g' "$file"

    # Supprimer "cacheNameProvider" dans l'appel à super(...)
    sed -i 's/, *cacheNameProvider//g' "$file"
    sed -i 's/cacheNameProvider, *//g' "$file"
    sed -i 's/cacheNameProvider//g' "$file"
done
