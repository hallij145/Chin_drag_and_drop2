package com.halliday.jack.draganddropchinese.database;

/**
 * Created by CharlieC on December/7/16.
 */

public class CharRadDbSchema {
    public static final class CharTable{
        public static final String NAME = "Chars";

        public static final class Cols{
            public static final String UUID = "_id";
            public static final String PINYIN = "pinyin";
            public static final String CHARAC = "charac";
            public static final String ENGLISH = "english";
            public static final String ISUSER = "isuser";
            public static final String RADICAL1 = "rad1";
            public static final String RADICAL2 = "rad2";
        }
    }
    public static final class RadTable{
        public static final String NAME = "Rads";

        public static final class Cols{
            public static final String UUID = "_id";
            public static final String PINYIN = "pinyin";
            public static final String CHARAC = "charac";
            public static final String ENGLISH = "english";
        }
    }
}