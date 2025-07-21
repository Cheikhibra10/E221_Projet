#!/bin/bash
set -e

echo "🚀 Starting cleanup of .env from Git history..."

# 1. Download BFG Repo-Cleaner if not present
if [ ! -f "bfg.jar" ]; then
  echo "⬇️ Downloading BFG Repo-Cleaner..."
  curl -L -o bfg.jar https://repo1.maven.org/maven2/com/madgag/bfg/1.14.0/bfg-1.14.0.jar
fi

# 2. Create a backup branch
echo "📦 Creating backup branch 'backup-before-bfg'..."
git branch backup-before-bfg

# 3. Run BFG to remove .env
echo "🧹 Cleaning all .env files from history..."
java -jar bfg.jar --delete-files .env

# 4. Cleanup and prune
echo "🧽 Cleaning up references..."
git reflog expire --expire=now --all
git gc --prune=now --aggressive

# 5. Force push
echo "🚢 Force pushing cleaned history to remote..."
git push origin master --force

echo "✅ Cleanup complete! A backup branch 'backup-before-bfg' was created."
