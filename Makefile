GRADLEW=gradlew

all:
	./$(GRADLEW) clean
	./$(GRADLEW) build
	./$(GRADLEW) refmapper