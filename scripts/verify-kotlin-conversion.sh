#!/usr/bin/env bash
set -euo pipefail

expected_patterns=21

pattern_readmes=$(find DesignPatterns -mindepth 2 -maxdepth 2 -name README.md | wc -l | tr -d ' ')
kotlin_examples=$(find DesignPatterns -mindepth 2 -maxdepth 2 -name Example.kt | wc -l | tr -d ' ')
swift_artifacts=$(find . \
  -name .git -prune -o \
  \( -name '*.swift' -o -name 'Package.swift' -o -name '*.xcworkspace' \) \
  -print)
swift_references=$(rg -n 'Swift|SwiftUI|Xcode|Package\.swift|\.swift' --glob '!scripts/verify-kotlin-conversion.sh' . || true)

if [[ "$pattern_readmes" != "$expected_patterns" ]]; then
  echo "Expected $expected_patterns pattern READMEs, found $pattern_readmes" >&2
  exit 1
fi

if [[ "$kotlin_examples" != "$expected_patterns" ]]; then
  echo "Expected $expected_patterns Kotlin examples, found $kotlin_examples" >&2
  exit 1
fi

if [[ -n "$swift_artifacts" ]]; then
  echo "Swift/Xcode artifacts remain:" >&2
  echo "$swift_artifacts" >&2
  exit 1
fi

if [[ -n "$swift_references" ]]; then
  echo "Swift/Xcode references remain:" >&2
  echo "$swift_references" >&2
  exit 1
fi

echo "Kotlin conversion verified: $pattern_readmes pattern READMEs and $kotlin_examples Kotlin examples."
