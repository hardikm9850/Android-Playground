#### Working 

### At build time, Hilt generates Dagger components for Android classes. It then walks through the code and performs the following steps:
1. Builds and validates dependency graphs, ensuring that there are no unsatisfied dependencies and no dependency cycles.
2. Generates the classes that it uses at runtime to create the actual objects and their dependencies.