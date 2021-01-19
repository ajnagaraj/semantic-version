package home.lab.desk;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SemanticVersionTest {

    @Test
    public void givenSemVer200_whenCompareWith210_then200LessThan210() {
        //given
        SemanticVersion semanticVersionTwo = SemanticVersion.parse("2.0.0");
        SemanticVersion semanticVersionTwoOne = SemanticVersion.parse("2.1.0");

        //when
        //then
        assertThat(semanticVersionTwo).isLessThan(semanticVersionTwoOne);
    }

    @Test
    public void givenSemVer2100_whenCompareWith248_then2100GreaterThan248() {
        //given
        SemanticVersion semanticVersionTwoTen = SemanticVersion.parse("2.10.0");
        SemanticVersion semanticVersionTwoFourEight = SemanticVersion.parse("2.4.8");

        //when
        //then
        assertThat(semanticVersionTwoFourEight).isLessThan(semanticVersionTwoTen);
    }

    @Test
    public void givenInvalidSemVer19888_whenParseVer_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String invalidSemVer = "1.9.8.9";

            //when
            SemanticVersion.parse(invalidSemVer);
        });

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [1.9.8.9] semantic version is invalid");
    }

    @Test
    public void givenSemVer3_whenCompareWithValidVer29999_then3GreaterThan19999() {
        //given
        SemanticVersion semVerThree = SemanticVersion.parse("3");
        SemanticVersion semVerTwoNineNine = SemanticVersion.parse("2.99.99");

        //when
        //then
        assertThat(semVerThree).isGreaterThan(semVerTwoNineNine);
    }

    @Test
    public void givenSemVer31_whenCompareWithSemVer29999_then3GreaterThan19999() {
        SemanticVersion semVerThreeOne = SemanticVersion.parse("3.1");
        SemanticVersion semVerTwoNineNine = SemanticVersion.parse("2.99.99");

        assertThat(semVerThreeOne).isGreaterThan(semVerTwoNineNine);
    }

    @Test
    public void givenSemVer001_whenCompareWithSemVer000_then001GreaterThan000() {
        SemanticVersion semVerZeroZeroOne = SemanticVersion.parse("0.0.1");
        SemanticVersion semVerZeroZeroZero = SemanticVersion.parse("0.0.0");

        assertThat(semVerZeroZeroOne).isGreaterThan(semVerZeroZeroZero);
    }

    @Test
    public void givenSemVer010_whenCompareWithSemVer011_then010LessThan011() {
        SemanticVersion semVerZeroOneZero = SemanticVersion.parse("0.1.0");
        SemanticVersion semVerZeroOneOne = SemanticVersion.parse("0.1.1");

        assertThat(semVerZeroOneZero).isLessThan(semVerZeroOneOne);
    }


    @Test
    public void givenInvalidAlphaNumericSemVer_whenParseSemver_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String invalidSemVer = "1-invalid.3+invalid.31";

            //when
            SemanticVersion.parse(invalidSemVer);
        });

        //when
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [1-invalid.3+invalid.31] semantic version is invalid");
    }

    @Test
    public void givenInvalidAlphaNumericMajorMinorSemVer_whenParseSemver_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String invalidSemVer = "1-invalid.3-invalid";

            //when
            SemanticVersion.parse(invalidSemVer);
        });

        //when
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [1-invalid.3-invalid] semantic version is invalid");
    }

    @Test
    public void givenInvalidAlphaNumericMajor_whenParseSemver_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String invalidSemVer = "1-invalid";

            //when
            SemanticVersion.parse(invalidSemVer);
        });

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [1-invalid] semantic version is invalid");
    }

    @Test
    public void givenInvalidAlphanumericMinor_whenParseSemver_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String invalidSemVer = "1-invalid.-invalid3";

            //when
            SemanticVersion.parse(invalidSemVer);
        });

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [1-invalid.-invalid3] semantic version is invalid");
    }

    @Test
    public void givenInvalidNonNumericVer_whenParseSemver_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () ->
                //when
                SemanticVersion.parse("invalid")
        );

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [invalid] semantic version is invalid");
    }

    @Test
    public void givenNullVer_whenParseSemver_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String nullVersion = null;

            //when
            SemanticVersion.parse(nullVersion);
        });

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Semantic version cannot be [null]");
    }

    @Test
    public void givenInvalidDelim_whenParseSemVer_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String invalidDelimSemVer = "2,4,5";

            //when
            SemanticVersion.parse(invalidDelimSemVer);
        });

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [2,4,5] semantic version is invalid");
    }

    @Test
    public void givenInvalidDelim_whenParseSemVerMajorMinor_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String invalidDelimSemVer = "2,4";

            //when
            SemanticVersion.parse(invalidDelimSemVer);
        });

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [2,4] semantic version is invalid");
    }

    @Test
    public void givenInvalidDelim_whenParseSemVerMajor_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String invalidDelimSemVer = "2,";

            //when
            SemanticVersion.parse(invalidDelimSemVer);
        });

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [2,] semantic version is invalid");
    }

    @Test
    public void givenSemVerWithSuffix_whenParseSemVer_thenIgnorePrefix() {
        //given
        String semVer = "1.32.1-alpha+build";

        //when
        SemanticVersion actualSemanticVersion = SemanticVersion.parse(semVer);

        //then
        String expectedSemVer = "1.32.1";
        SemanticVersion expectedSemanticVersion = SemanticVersion.parse(expectedSemVer);

        assertThat(actualSemanticVersion).isEqualTo(expectedSemanticVersion);
        assertThat(actualSemanticVersion.toString()).isEqualTo(expectedSemVer);
    }

    @Test
    public void givenMajorSemVer_whenParseSemVer_thenMinorPatchZero() {
        //given
        String semVer = "1";

        //when
        SemanticVersion actualSemanticVersion = SemanticVersion.parse(semVer);

        //then
        SemanticVersion expectedSemanticVersion = SemanticVersion.parse("1.0.0");
        assertThat(actualSemanticVersion).isEqualTo(expectedSemanticVersion);
    }

    @Test
    public void givenMajorMinorSemVer_whenParseSemVer_thenPatchZero() {
        //given
        String semVer = "1.7";

        //when
        SemanticVersion actualSemanticVersion = SemanticVersion.parse(semVer);

        //then
        SemanticVersion expectedSemanticVersion = SemanticVersion.parse("1.7.0");
        assertThat(actualSemanticVersion).isEqualTo(expectedSemanticVersion);
    }

    @Test
    public void givenSemVer232_whenParse_thenEqualTo232() {
        //given
        String semVer = "2.3.2";

        //when
        SemanticVersion actualSemanticVersion = SemanticVersion.parse(semVer);

        //then
        SemanticVersion expectedSemanticVersion = SemanticVersion.parse("2.3.2");
        assertThat(actualSemanticVersion).isEqualTo(expectedSemanticVersion);
    }

    @Test
    public void givenNegativeSemVer_whenParseSemVer_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String semVer = "-1.-32.-1";

            //when
            SemanticVersion.parse(semVer);
        });

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [-1.-32.-1] semantic version is invalid");
    }

    @Test
    public void givenNegativeMajorMinorSemVer_whenParseSemVer_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String semVer = "-1.-32";

            //when
            SemanticVersion.parse(semVer);
        });

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [-1.-32] semantic version is invalid");
    }

    @Test
    public void givenNegativeMajorSemVer_whenParseSemVer_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            String semVer = "-1";

            //when
            SemanticVersion.parse(semVer);
        });

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [-1] semantic version is invalid");
    }

    @Test
    public void givenMajorMinorPatchNumbers_whenNewInstance_thenCreateSemVer() {
        //given
        int major = 1;
        int minor = 0;
        int patch = 12;

        //when
        SemanticVersion semanticVersion = SemanticVersion.newInstance(major, minor, patch);

        //then
        assertThat(semanticVersion.getMajor()).isEqualTo(1);
        assertThat(semanticVersion.getMinor()).isEqualTo(0);
        assertThat(semanticVersion.getPatch()).isEqualTo(12);
    }

    @Test
    public void givenNegativeMajorMinorPatchNumbers_whenNewInstance_thenThrowIllegalArgumentException() {
        Exception illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            //given
            int negativeMajor = -10;
            int negativeMinor = -12;
            int negativePatch = -3;

            //when
            SemanticVersion.newInstance(negativeMajor, negativeMinor, negativePatch);
        });

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Given [-10.-12.-3] semantic version is invalid");
    }

    @Test
    public void givenSemVer_whenToString_thenReturnSemVerFormat() {
        //given
        String semVer = "1";

        //when
        SemanticVersion actualSemVer = SemanticVersion.parse(semVer);

        //then
        String expectedSemVer = "1.0.0";
        assertThat(actualSemVer.toString()).isEqualTo(expectedSemVer);
    }

}
