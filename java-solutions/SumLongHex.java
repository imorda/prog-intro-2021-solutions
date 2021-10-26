public class SumLongHex {
    public static void main(String[] args) {
        long answer = 0;

        for (String arg : args) {
            int leftNumberBorder = -1;
            for (int j = 0; j < arg.length(); j++) {
                if (Character.isWhitespace(arg.charAt(j))) {
                    if (leftNumberBorder >= 0) {
                        answer += tryParseSubstring(arg, leftNumberBorder, j);
                        leftNumberBorder = -1;
                    }
                } else if (leftNumberBorder < 0) {
                    leftNumberBorder = j;
                }
            }
            answer += tryParseSubstring(arg, leftNumberBorder, arg.length());
        }
        System.out.println(answer);
    }

    private static long tryParseSubstring(String string, int l, int r){
        if(l >= 0){
            try{
                return Long.parseLong(string.substring(l, r));
            } catch (NumberFormatException e){
                return Long.parseUnsignedLong(string.substring(l + 2, r), 16);
            }
        }
        return 0;
    }
}