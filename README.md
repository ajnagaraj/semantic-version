#### About
A representation of semantic versions which ignores any additional labels for pre-release, build and snapshots.

#### Build
The project uses `gradle` as its build automation tool. It can be built by using `./gradlew build`

#### Usage
Parsing
```
SemanticVersion.parse("2.3.4")

//missing minor and patch sections are defaulted to zero
SemanticVerson semanticVersion = SemanticVersion.parse("1")
semanticVersion.toString().equals("1.0.0") is true

//missing patch section is defaulted to zero
SemanticVerson semanticVersion = SemanticVersion.parse("1.8")
semanticVersion.toString().equals("1.8.0") is true

//additional labels are ignored
SemanticVersion semanticVersion = SemanticVersion.parse("1.4.3-snapshot")
semanticVersion.toString().equals("1.4.3") is true

//invalid versions throw an IllegalArgumentException
SemanticVersion.parse("1.9.8.9")
SemanticVersion.parse("-1.-3.-4")
SemanticVersion.parse("-invalid.1-invalid")

```

Comparing
```
//2.0.0 < 2.0.1
SemanticVersion twoZeroZero = SemanticVersion.parse("2.0.0")
SemanticVersion twoZeroOne = SemanticVersion.parse("2.0.1")
twoZeroZero.compareTo(twoZeroOne) < 1

//2.10.0 > 2.4.8
SemanticVersion twoTenZero = SemanticVersion.parse("2.10.0")
SemanticVersion twoFourEight = SemanticVersion.parse("2.4.8")
twoTenZero.compareTo(twoFourEight) > 1

//3 > 2.99.99
SemanticVersion three = SemanticVersion.parse("3")
SemanticVersion twoNinetyNineNinetyNine = SemanticVersion.parse("2.99.99")
three.compareTo(twoNinetyNineNinetyNine) > 1

//0.1.0 < 0.1.1
SemanticVersion zeroOneZero = SemanticVersion.parse("0.1.0")
SemanticVersion zeroOneOne = SemanticVersion.parse("0.1.1")
zeroOneZero.compareTo(zeroOneOne) < 1

```
