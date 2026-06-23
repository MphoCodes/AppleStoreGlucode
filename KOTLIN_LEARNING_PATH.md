# Kotlin Learning Path

This repo is meant to teach design patterns through Kotlin and Android architecture vocabulary. The examples deliberately avoid Android framework imports so the core pattern roles stay easy to see, then each README points you toward where the idea usually appears in an Android app.

## 1. Start With Creation Patterns

Read these first when you are learning how objects get created and configured:

- [Factory Method](DesignPatterns/AppleStoreFactoryMethod/README.md): choose one shipping implementation from a request type.
- [Abstract Factory](DesignPatterns/AppleStoreAbstractFactory/README.md): create a matching family of UI collaborators.
- [Builder](DesignPatterns/AppleStoreBuilder/README.md): configure a larger value object step by step.
- [Prototype](DesignPatterns/AppleStorePrototype/README.md): copy a configured listing and adjust the variant.
- [Singleton](DesignPatterns/AppleStoreSingleton/README.md): understand when Kotlin `object` is appropriate and when it can hide global state.

Android mapping: repositories, factories, dependency injection modules, product configuration flows, and analytics helpers.

## 2. Move To Structural Patterns

Read these when you are learning how classes and interfaces fit together:

- [Adapter](DesignPatterns/AppleStoreAdapter/README.md): translate a legacy API into the shape the app wants.
- [Bridge](DesignPatterns/AppleStoreBridge/README.md): vary checkout flows independently from payment processors.
- [Composite](DesignPatterns/AppleStoreComposite/README.md): treat products and bundles through one interface.
- [Decorator](DesignPatterns/AppleStoreDecorator/README.md): add order-line behavior by wrapping it.
- [Facade](DesignPatterns/AppleStoreFacade/README.md): place a simple API over inventory, payment, and fulfilment services.
- [Proxy](DesignPatterns/AppleStoreProxy/README.md): add caching around a repository.

Android mapping: data-source adapters, repository wrappers, feature facades, cache layers, and UI item models.

## 3. Finish With Behaviour Patterns

Read these when you are learning how behavior changes over time:

- [Chain of Responsibility](DesignPatterns/AppleStoreChainOfResponsibility/README.md): route support requests through escalating handlers.
- [Command](DesignPatterns/AppleStoreCommand/README.md): represent cart actions as queueable operations.
- [Iterator](DesignPatterns/AppleStoreIterator/README.md): traverse catalog data with Kotlin collections and sequences.
- [Mediator](DesignPatterns/AppleStoreMediator/README.md): coordinate screen components without coupling them directly.
- [Memento](DesignPatterns/AppleStoreMemento/README.md): save and restore cart state.
- [Observer](DesignPatterns/AppleStoreObserver/README.md): notify subscribers about stock updates.
- [State](DesignPatterns/AppleStoreState/README.md): move order-state behavior into state objects.
- [Strategy](DesignPatterns/AppleStoreStrategy/README.md): swap discount algorithms at runtime.
- [Template Method](DesignPatterns/AppleStoreTemplateMethod/README.md): keep a fixed launch checklist while customising steps.
- [Visitor](DesignPatterns/AppleStoreVisitor/README.md): add operations across item types without editing those item types.

Android mapping: UI state machines, command queues, event streams, navigation coordinators, pricing policies, and offline restore flows.

## Practice Routine

For each pattern:

1. Read the README intent in your own words.
2. Open `Example.kt` and identify the pattern roles.
3. Rewrite the Apple Store domain into a feature you know, such as login, cart, onboarding, rewards, or product details.
4. Decide whether the pattern makes the design simpler. If it only adds ceremony, do not use it.

## Kotlin Features To Notice

- `interface` keeps behavior replaceable and testable.
- `data class` makes state snapshots and value objects explicit.
- `object` creates a process-wide singleton.
- `fun interface` is useful for single-method callback contracts.
- `Sequence` is useful when traversal can stay lazy.
- Expression-bodied functions keep small examples readable.
