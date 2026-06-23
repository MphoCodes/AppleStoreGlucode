# Other Kotlin Patterns

These are Kotlin/Android idioms that often sit beside the classic design patterns.

- Lazy initialization: use `by lazy` for values that are expensive and can be created on first use.
- Null object: provide a harmless implementation instead of scattering null checks.
- Delegation: use Kotlin `by` delegation when one class should forward behavior to another.
- Module pattern: use package boundaries, dependency injection modules, and internal visibility instead of platform-specific module conventions.
