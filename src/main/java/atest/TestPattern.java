package atest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {
  private static final Pattern PATTERN = Pattern.compile("\\d+");

  /**
   * @param args
   */
  public static void main(final String[] args) {
    //Ceci est un test et 123 est censé être l'identifiant de l'objet que je veux récupérer.
    final Matcher matcher = PATTERN.matcher("NOMTABLE123");

    if (matcher.find()) {
      System.out.println(Integer.parseInt(matcher.group()));
    }
  }

}
