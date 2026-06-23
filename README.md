# Android Design Patterns in Kotlin

This repository is a Kotlin-first learning version of the Apple Store design-pattern examples. The imported package structure has been converted into Android/Kotlin knowledge: each pattern keeps the Apple Store domain, explains where the pattern fits in an Android codebase, and includes a focused Kotlin example.

## How to Use This Repo

- Read the pattern README first for intent, Android usage, and Kotlin-specific notes.
- Open the matching `Example.kt` file for the compact implementation.
- Treat the examples as learning references, not production-ready app modules.
- Translate the ideas into ViewModels, repositories, use cases, UI state holders, and service adapters as appropriate.
- Follow the [Kotlin learning path](KOTLIN_LEARNING_PATH.md) when you want a structured route through the patterns.

## Pattern Catalog

- [Abstract Factory](DesignPatterns/AppleStoreAbstractFactory/README.md)
- [Adapter](DesignPatterns/AppleStoreAdapter/README.md)
- [Bridge](DesignPatterns/AppleStoreBridge/README.md)
- [Builder](DesignPatterns/AppleStoreBuilder/README.md)
- [Chain of Responsibility](DesignPatterns/AppleStoreChainOfResponsibility/README.md)
- [Command](DesignPatterns/AppleStoreCommand/README.md)
- [Composite](DesignPatterns/AppleStoreComposite/README.md)
- [Decorator](DesignPatterns/AppleStoreDecorator/README.md)
- [Facade](DesignPatterns/AppleStoreFacade/README.md)
- [Factory Method](DesignPatterns/AppleStoreFactoryMethod/README.md)
- [Iterator](DesignPatterns/AppleStoreIterator/README.md)
- [Mediator](DesignPatterns/AppleStoreMediator/README.md)
- [Memento](DesignPatterns/AppleStoreMemento/README.md)
- [Observer](DesignPatterns/AppleStoreObserver/README.md)
- [Prototype](DesignPatterns/AppleStorePrototype/README.md)
- [Proxy](DesignPatterns/AppleStoreProxy/README.md)
- [Singleton](DesignPatterns/AppleStoreSingleton/README.md)
- [State](DesignPatterns/AppleStoreState/README.md)
- [Strategy](DesignPatterns/AppleStoreStrategy/README.md)
- [Template Method](DesignPatterns/AppleStoreTemplateMethod/README.md)
- [Visitor](DesignPatterns/AppleStoreVisitor/README.md)


## Kotlin/Android Framing

- Prefer `interface` for replaceable behavior and testing seams.
- Prefer `data class` for immutable state snapshots and simple value objects.
- Prefer `object` only when one process-wide instance is intentional.
- Use `sealed interface` or `sealed class` for closed state sets in real Android UI state models.
- Keep Android framework types out of core pattern examples until the boundary is useful.

## Verification

Run the local conversion check after edits:

```bash
./scripts/verify-kotlin-conversion.sh
```

The script confirms the expected pattern count, Kotlin example coverage, and absence of previous platform source artifacts.

## Reference

- Design Patterns: Elements of Reusable Object-Oriented Software - Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides
