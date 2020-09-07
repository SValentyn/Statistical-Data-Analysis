// Generated by Snowball 2.0.0 - https://snowballstem.org/

package libstemmer_java.java.org.tartarus.snowball.ext;

import libstemmer_java.java.org.tartarus.snowball.Among;
import libstemmer_java.java.org.tartarus.snowball.SnowballStemmer;

/**
 * This class implements the stemming algorithm defined by a snowball script.
 * <p>
 * Generated by Snowball 2.0.0 - https://snowballstem.org/
 * </p>
 */
@SuppressWarnings("unused")
public class EnglishStemmer extends SnowballStemmer {
    
    private static final long serialVersionUID = 1L;
    
    private final static Among[] a_0 = {
            new Among("arsen", -1, -1),
            new Among("commun", -1, -1),
            new Among("gener", -1, -1)
    };
    
    private final static Among[] a_1 = {
            new Among("'", -1, 1),
            new Among("'s'", 0, 1),
            new Among("'s", -1, 1)
    };
    
    private final static Among[] a_2 = {
            new Among("ied", -1, 2),
            new Among("s", -1, 3),
            new Among("ies", 1, 2),
            new Among("sses", 1, 1),
            new Among("ss", 1, -1),
            new Among("us", 1, -1)
    };
    
    private final static Among[] a_3 = {
            new Among("", -1, 3),
            new Among("bb", 0, 2),
            new Among("dd", 0, 2),
            new Among("ff", 0, 2),
            new Among("gg", 0, 2),
            new Among("bl", 0, 1),
            new Among("mm", 0, 2),
            new Among("nn", 0, 2),
            new Among("pp", 0, 2),
            new Among("rr", 0, 2),
            new Among("at", 0, 1),
            new Among("tt", 0, 2),
            new Among("iz", 0, 1)
    };
    
    private final static Among[] a_4 = {
            new Among("ed", -1, 2),
            new Among("eed", 0, 1),
            new Among("ing", -1, 2),
            new Among("edly", -1, 2),
            new Among("eedly", 3, 1),
            new Among("ingly", -1, 2)
    };
    
    private final static Among[] a_5 = {
            new Among("anci", -1, 3),
            new Among("enci", -1, 2),
            new Among("ogi", -1, 13),
            new Among("li", -1, 15),
            new Among("bli", 3, 12),
            new Among("abli", 4, 4),
            new Among("alli", 3, 8),
            new Among("fulli", 3, 9),
            new Among("lessli", 3, 14),
            new Among("ousli", 3, 10),
            new Among("entli", 3, 5),
            new Among("aliti", -1, 8),
            new Among("biliti", -1, 12),
            new Among("iviti", -1, 11),
            new Among("tional", -1, 1),
            new Among("ational", 14, 7),
            new Among("alism", -1, 8),
            new Among("ation", -1, 7),
            new Among("ization", 17, 6),
            new Among("izer", -1, 6),
            new Among("ator", -1, 7),
            new Among("iveness", -1, 11),
            new Among("fulness", -1, 9),
            new Among("ousness", -1, 10)
    };
    
    private final static Among[] a_6 = {
            new Among("icate", -1, 4),
            new Among("ative", -1, 6),
            new Among("alize", -1, 3),
            new Among("iciti", -1, 4),
            new Among("ical", -1, 4),
            new Among("tional", -1, 1),
            new Among("ational", 5, 2),
            new Among("ful", -1, 5),
            new Among("ness", -1, 5)
    };
    
    private final static Among[] a_7 = {
            new Among("ic", -1, 1),
            new Among("ance", -1, 1),
            new Among("ence", -1, 1),
            new Among("able", -1, 1),
            new Among("ible", -1, 1),
            new Among("ate", -1, 1),
            new Among("ive", -1, 1),
            new Among("ize", -1, 1),
            new Among("iti", -1, 1),
            new Among("al", -1, 1),
            new Among("ism", -1, 1),
            new Among("ion", -1, 2),
            new Among("er", -1, 1),
            new Among("ous", -1, 1),
            new Among("ant", -1, 1),
            new Among("ent", -1, 1),
            new Among("ment", 15, 1),
            new Among("ement", 16, 1)
    };
    
    private final static Among[] a_8 = {
            new Among("e", -1, 1),
            new Among("l", -1, 2)
    };
    
    private final static Among[] a_9 = {
            new Among("succeed", -1, -1),
            new Among("proceed", -1, -1),
            new Among("exceed", -1, -1),
            new Among("canning", -1, -1),
            new Among("inning", -1, -1),
            new Among("earring", -1, -1),
            new Among("herring", -1, -1),
            new Among("outing", -1, -1)
    };
    
    private final static Among[] a_10 = {
            new Among("andes", -1, -1),
            new Among("atlas", -1, -1),
            new Among("bias", -1, -1),
            new Among("cosmos", -1, -1),
            new Among("dying", -1, 3),
            new Among("early", -1, 9),
            new Among("gently", -1, 7),
            new Among("howe", -1, -1),
            new Among("idly", -1, 6),
            new Among("lying", -1, 4),
            new Among("news", -1, -1),
            new Among("only", -1, 10),
            new Among("singly", -1, 11),
            new Among("skies", -1, 2),
            new Among("skis", -1, 1),
            new Among("sky", -1, -1),
            new Among("tying", -1, 5),
            new Among("ugly", -1, 8)
    };
    
    private static final char[] g_v = { 17, 65, 16, 1 };
    
    private static final char[] g_v_WXY = { 1, 17, 65, 208, 1 };
    
    private static final char[] g_valid_LI = { 55, 141, 2 };
    
    private boolean B_Y_found;
    private int I_p2;
    private int I_p1;
    
    
    private boolean r_prelude() {
        // (, line 25
        // unset Y_found, line 26
        B_Y_found = false;
        // do, line 27
        int v_1 = cursor;
        lab0:
        {
            // (, line 27
            // [, line 27
            bra = cursor;
            // literal, line 27
            if (!(eq_s("'"))) {
                break lab0;
            }
            // ], line 27
            ket = cursor;
            // delete, line 27
            slice_del();
        }
        cursor = v_1;
        // do, line 28
        int v_2 = cursor;
        lab1:
        {
            // (, line 28
            // [, line 28
            bra = cursor;
            // literal, line 28
            if (!(eq_s("y"))) {
                break lab1;
            }
            // ], line 28
            ket = cursor;
            // <-, line 28
            slice_from("Y");
            // set Y_found, line 28
            B_Y_found = true;
        }
        cursor = v_2;
        // do, line 29
        int v_3 = cursor;
        lab2:
        {
            // repeat, line 29
            while (true) {
                int v_4 = cursor;
                lab3:
                {
                    // (, line 29
                    // goto, line 29
                    golab4:
                    while (true) {
                        int v_5 = cursor;
                        lab5:
                        {
                            // (, line 29
                            if (!(in_grouping(g_v, 97, 121))) {
                                break lab5;
                            }
                            // [, line 29
                            bra = cursor;
                            // literal, line 29
                            if (!(eq_s("y"))) {
                                break lab5;
                            }
                            // ], line 29
                            ket = cursor;
                            cursor = v_5;
                            break golab4;
                        }
                        cursor = v_5;
                        if (cursor >= limit) {
                            break lab3;
                        }
                        cursor++;
                    }
                    // <-, line 29
                    slice_from("Y");
                    // set Y_found, line 29
                    B_Y_found = true;
                    continue;
                }
                cursor = v_4;
                break;
            }
        }
        cursor = v_3;
        return true;
    }
    
    private boolean r_mark_regions() {
        // (, line 32
        I_p1 = limit;
        I_p2 = limit;
        // do, line 35
        int v_1 = cursor;
        lab0:
        {
            // (, line 35
            // or, line 41
            lab1:
            {
                int v_2 = cursor;
                lab2:
                {
                    // among, line 36
                    if (find_among(a_0) == 0) {
                        break lab2;
                    }
                    break lab1;
                }
                cursor = v_2;
                // (, line 41
                // gopast, line 41
                golab3:
                while (true) {
                    lab4:
                    {
                        if (!(in_grouping(g_v, 97, 121))) {
                            break lab4;
                        }
                        break golab3;
                    }
                    if (cursor >= limit) {
                        break lab0;
                    }
                    cursor++;
                }
                // gopast, line 41
                golab5:
                while (true) {
                    lab6:
                    {
                        if (!(out_grouping(g_v, 97, 121))) {
                            break lab6;
                        }
                        break golab5;
                    }
                    if (cursor >= limit) {
                        break lab0;
                    }
                    cursor++;
                }
            }
            // setmark p1, line 42
            I_p1 = cursor;
            // gopast, line 43
            golab7:
            while (true) {
                lab8:
                {
                    if (!(in_grouping(g_v, 97, 121))) {
                        break lab8;
                    }
                    break golab7;
                }
                if (cursor >= limit) {
                    break lab0;
                }
                cursor++;
            }
            // gopast, line 43
            golab9:
            while (true) {
                lab10:
                {
                    if (!(out_grouping(g_v, 97, 121))) {
                        break lab10;
                    }
                    break golab9;
                }
                if (cursor >= limit) {
                    break lab0;
                }
                cursor++;
            }
            // setmark p2, line 43
            I_p2 = cursor;
        }
        cursor = v_1;
        return true;
    }
    
    private boolean r_shortv() {
        // (, line 49
        // or, line 51
        lab0:
        {
            int v_1 = limit - cursor;
            lab1:
            {
                // (, line 50
                if (!(out_grouping_b(g_v_WXY, 89, 121))) {
                    break lab1;
                }
                if (!(in_grouping_b(g_v, 97, 121))) {
                    break lab1;
                }
                if (!(out_grouping_b(g_v, 97, 121))) {
                    break lab1;
                }
                break lab0;
            }
            cursor = limit - v_1;
            // (, line 52
            if (!(out_grouping_b(g_v, 97, 121))) {
                return false;
            }
            if (!(in_grouping_b(g_v, 97, 121))) {
                return false;
            }
            // atlimit, line 52
            if (cursor > limit_backward) {
                return false;
            }
        }
        return true;
    }
    
    private boolean r_R1() {
        return I_p1 <= cursor;
    }
    
    private boolean r_R2() {
        return I_p2 <= cursor;
    }
    
    private boolean r_Step_1a() {
        int among_var;
        // (, line 58
        // try, line 59
        int v_1 = limit - cursor;
        lab0:
        {
            // (, line 59
            // [, line 60
            ket = cursor;
            // substring, line 60
            if (find_among_b(a_1) == 0) {
                cursor = limit - v_1;
                break lab0;
            }
            // ], line 60
            bra = cursor;
            // (, line 62
            // delete, line 62
            slice_del();
        }
        // [, line 65
        ket = cursor;
        // substring, line 65
        among_var = find_among_b(a_2);
        if (among_var == 0) {
            return false;
        }
        // ], line 65
        bra = cursor;
        switch (among_var) {
            case 1:
                // (, line 66
                // <-, line 66
                slice_from("ss");
                break;
            case 2:
                // (, line 68
                // or, line 68
                lab1:
                {
                    int v_2 = limit - cursor;
                    lab2:
                    {
                        // (, line 68
                        // hop, line 68
                        {
                            int c = cursor - 2;
                            if (limit_backward > c || c > limit) {
                                break lab2;
                            }
                            cursor = c;
                        }
                        // <-, line 68
                        slice_from("i");
                        break lab1;
                    }
                    cursor = limit - v_2;
                    // <-, line 68
                    slice_from("ie");
                }
                break;
            case 3:
                // (, line 69
                // next, line 69
                if (cursor <= limit_backward) {
                    return false;
                }
                cursor--;
                // gopast, line 69
                golab3:
                while (true) {
                    lab4:
                    {
                        if (!(in_grouping_b(g_v, 97, 121))) {
                            break lab4;
                        }
                        break golab3;
                    }
                    if (cursor <= limit_backward) {
                        return false;
                    }
                    cursor--;
                }
                // delete, line 69
                slice_del();
                break;
        }
        return true;
    }
    
    private boolean r_Step_1b() {
        int among_var;
        // (, line 74
        // [, line 75
        ket = cursor;
        // substring, line 75
        among_var = find_among_b(a_4);
        if (among_var == 0) {
            return false;
        }
        // ], line 75
        bra = cursor;
        switch (among_var) {
            case 1:
                // (, line 77
                // call R1, line 77
                if (!r_R1()) {
                    return false;
                }
                // <-, line 77
                slice_from("ee");
                break;
            case 2:
                // (, line 79
                // test, line 80
                int v_1 = limit - cursor;
                // gopast, line 80
                golab0:
                while (true) {
                    lab1:
                    {
                        if (!(in_grouping_b(g_v, 97, 121))) {
                            break lab1;
                        }
                        break golab0;
                    }
                    if (cursor <= limit_backward) {
                        return false;
                    }
                    cursor--;
                }
                cursor = limit - v_1;
                // delete, line 80
                slice_del();
                // test, line 81
                int v_3 = limit - cursor;
                // substring, line 81
                among_var = find_among_b(a_3);
                if (among_var == 0) {
                    return false;
                }
                cursor = limit - v_3;
                switch (among_var) {
                    case 1:
                        // (, line 83
                        // <+, line 83
                    {
                        int c = cursor;
                        insert(cursor, cursor, "e");
                        cursor = c;
                    }
                    break;
                    case 2:
                        // (, line 86
                        // [, line 86
                        ket = cursor;
                        // next, line 86
                        if (cursor <= limit_backward) {
                            return false;
                        }
                        cursor--;
                        // ], line 86
                        bra = cursor;
                        // delete, line 86
                        slice_del();
                        break;
                    case 3:
                        // (, line 87
                        // atmark, line 87
                        if (cursor != I_p1) {
                            return false;
                        }
                        // test, line 87
                        int v_4 = limit - cursor;
                        // call shortv, line 87
                        if (!r_shortv()) {
                            return false;
                        }
                        cursor = limit - v_4;
                        // <+, line 87
                    {
                        int c = cursor;
                        insert(cursor, cursor, "e");
                        cursor = c;
                    }
                    break;
                }
                break;
        }
        return true;
    }
    
    private boolean r_Step_1c() {
        // (, line 93
        // [, line 94
        ket = cursor;
        // or, line 94
        lab0:
        {
            int v_1 = limit - cursor;
            lab1:
            {
                // literal, line 94
                if (!(eq_s_b("y"))) {
                    break lab1;
                }
                break lab0;
            }
            cursor = limit - v_1;
            // literal, line 94
            if (!(eq_s_b("Y"))) {
                return false;
            }
        }
        // ], line 94
        bra = cursor;
        if (!(out_grouping_b(g_v, 97, 121))) {
            return false;
        }
        // not, line 95
        lab2:
        {
            // atlimit, line 95
            if (cursor > limit_backward) {
                break lab2;
            }
            return false;
        }
        // <-, line 96
        slice_from("i");
        return true;
    }
    
    private boolean r_Step_2() {
        int among_var;
        // (, line 99
        // [, line 100
        ket = cursor;
        // substring, line 100
        among_var = find_among_b(a_5);
        if (among_var == 0) {
            return false;
        }
        // ], line 100
        bra = cursor;
        // call R1, line 100
        if (!r_R1()) {
            return false;
        }
        switch (among_var) {
            case 1:
                // (, line 101
                // <-, line 101
                slice_from("tion");
                break;
            case 2:
                // (, line 102
                // <-, line 102
                slice_from("ence");
                break;
            case 3:
                // (, line 103
                // <-, line 103
                slice_from("ance");
                break;
            case 4:
                // (, line 104
                // <-, line 104
                slice_from("able");
                break;
            case 5:
                // (, line 105
                // <-, line 105
                slice_from("ent");
                break;
            case 6:
                // (, line 107
                // <-, line 107
                slice_from("ize");
                break;
            case 7:
                // (, line 109
                // <-, line 109
                slice_from("ate");
                break;
            case 8:
                // (, line 111
                // <-, line 111
                slice_from("al");
                break;
            case 9:
                // (, line 112
                // <-, line 112
                slice_from("ful");
                break;
            case 10:
                // (, line 114
                // <-, line 114
                slice_from("ous");
                break;
            case 11:
                // (, line 116
                // <-, line 116
                slice_from("ive");
                break;
            case 12:
                // (, line 118
                // <-, line 118
                slice_from("ble");
                break;
            case 13:
                // (, line 119
                // literal, line 119
                if (!(eq_s_b("l"))) {
                    return false;
                }
                // <-, line 119
                slice_from("og");
                break;
            case 14:
                // (, line 121
                // <-, line 121
                slice_from("less");
                break;
            case 15:
                // (, line 122
                if (!(in_grouping_b(g_valid_LI, 99, 116))) {
                    return false;
                }
                // delete, line 122
                slice_del();
                break;
        }
        return true;
    }
    
    private boolean r_Step_3() {
        int among_var;
        // (, line 126
        // [, line 127
        ket = cursor;
        // substring, line 127
        among_var = find_among_b(a_6);
        if (among_var == 0) {
            return false;
        }
        // ], line 127
        bra = cursor;
        // call R1, line 127
        if (!r_R1()) {
            return false;
        }
        switch (among_var) {
            case 1:
                // (, line 128
                // <-, line 128
                slice_from("tion");
                break;
            case 2:
                // (, line 129
                // <-, line 129
                slice_from("ate");
                break;
            case 3:
                // (, line 130
                // <-, line 130
                slice_from("al");
                break;
            case 4:
                // (, line 132
                // <-, line 132
                slice_from("ic");
                break;
            case 5:
                // (, line 134
                // delete, line 134
                slice_del();
                break;
            case 6:
                // (, line 136
                // call R2, line 136
                if (!r_R2()) {
                    return false;
                }
                // delete, line 136
                slice_del();
                break;
        }
        return true;
    }
    
    private boolean r_Step_4() {
        int among_var;
        // (, line 140
        // [, line 141
        ket = cursor;
        // substring, line 141
        among_var = find_among_b(a_7);
        if (among_var == 0) {
            return false;
        }
        // ], line 141
        bra = cursor;
        // call R2, line 141
        if (!r_R2()) {
            return false;
        }
        switch (among_var) {
            case 1:
                // (, line 144
                // delete, line 144
                slice_del();
                break;
            case 2:
                // (, line 145
                // or, line 145
                lab0:
                {
                    int v_1 = limit - cursor;
                    lab1:
                    {
                        // literal, line 145
                        if (!(eq_s_b("s"))) {
                            break lab1;
                        }
                        break lab0;
                    }
                    cursor = limit - v_1;
                    // literal, line 145
                    if (!(eq_s_b("t"))) {
                        return false;
                    }
                }
                // delete, line 145
                slice_del();
                break;
        }
        return true;
    }
    
    private boolean r_Step_5() {
        int among_var;
        // (, line 149
        // [, line 150
        ket = cursor;
        // substring, line 150
        among_var = find_among_b(a_8);
        if (among_var == 0) {
            return false;
        }
        // ], line 150
        bra = cursor;
        switch (among_var) {
            case 1:
                // (, line 151
                // or, line 151
                lab0:
                {
                    int v_1 = limit - cursor;
                    lab1:
                    {
                        // call R2, line 151
                        if (!r_R2()) {
                            break lab1;
                        }
                        break lab0;
                    }
                    cursor = limit - v_1;
                    // (, line 151
                    // call R1, line 151
                    if (!r_R1()) {
                        return false;
                    }
                    // not, line 151
                    {
                        int v_2 = limit - cursor;
                        lab2:
                        {
                            // call shortv, line 151
                            if (!r_shortv()) {
                                break lab2;
                            }
                            return false;
                        }
                        cursor = limit - v_2;
                    }
                }
                // delete, line 151
                slice_del();
                break;
            case 2:
                // (, line 152
                // call R2, line 152
                if (!r_R2()) {
                    return false;
                }
                // literal, line 152
                if (!(eq_s_b("l"))) {
                    return false;
                }
                // delete, line 152
                slice_del();
                break;
        }
        return true;
    }
    
    private boolean r_exception2() {
        // (, line 156
        // [, line 158
        ket = cursor;
        // substring, line 158
        if (find_among_b(a_9) == 0) {
            return false;
        }
        // ], line 158
        bra = cursor;
        // atlimit, line 158
        return cursor <= limit_backward;
    }
    
    private boolean r_exception1() {
        int among_var;
        // (, line 168
        // [, line 170
        bra = cursor;
        // substring, line 170
        among_var = find_among(a_10);
        if (among_var == 0) {
            return false;
        }
        // ], line 170
        ket = cursor;
        // atlimit, line 170
        if (cursor < limit) {
            return false;
        }
        switch (among_var) {
            case 1:
                // (, line 174
                // <-, line 174
                slice_from("ski");
                break;
            case 2:
                // (, line 175
                // <-, line 175
                slice_from("sky");
                break;
            case 3:
                // (, line 176
                // <-, line 176
                slice_from("die");
                break;
            case 4:
                // (, line 177
                // <-, line 177
                slice_from("lie");
                break;
            case 5:
                // (, line 178
                // <-, line 178
                slice_from("tie");
                break;
            case 6:
                // (, line 182
                // <-, line 182
                slice_from("idl");
                break;
            case 7:
                // (, line 183
                // <-, line 183
                slice_from("gentl");
                break;
            case 8:
                // (, line 184
                // <-, line 184
                slice_from("ugli");
                break;
            case 9:
                // (, line 185
                // <-, line 185
                slice_from("earli");
                break;
            case 10:
                // (, line 186
                // <-, line 186
                slice_from("onli");
                break;
            case 11:
                // (, line 187
                // <-, line 187
                slice_from("singl");
                break;
        }
        return true;
    }
    
    private boolean r_postlude() {
        // (, line 203
        // Boolean test Y_found, line 203
        if (!(B_Y_found)) {
            return false;
        }
        // repeat, line 203
        while (true) {
            int v_1 = cursor;
            lab0:
            {
                // (, line 203
                // goto, line 203
                golab1:
                while (true) {
                    int v_2 = cursor;
                    lab2:
                    {
                        // (, line 203
                        // [, line 203
                        bra = cursor;
                        // literal, line 203
                        if (!(eq_s("Y"))) {
                            break lab2;
                        }
                        // ], line 203
                        ket = cursor;
                        cursor = v_2;
                        break golab1;
                    }
                    cursor = v_2;
                    if (cursor >= limit) {
                        break lab0;
                    }
                    cursor++;
                }
                // <-, line 203
                slice_from("y");
                continue;
            }
            cursor = v_1;
            break;
        }
        return true;
    }
    
    public boolean stem() {
        // (, line 205
        // or, line 207
        lab0:
        {
            int v_1 = cursor;
            lab1:
            {
                // call exception1, line 207
                if (!r_exception1()) {
                    break lab1;
                }
                break lab0;
            }
            cursor = v_1;
            lab2:
            {
                // not, line 208
                {
                    int v_2 = cursor;
                    lab3:
                    {
                        // hop, line 208
                        {
                            int c = cursor + 3;
                            if (0 > c || c > limit) {
                                break lab3;
                            }
                            cursor = c;
                        }
                        break lab2;
                    }
                    cursor = v_2;
                }
                break lab0;
            }
            cursor = v_1;
            // (, line 208
            // do, line 209
            // call prelude, line 209
            r_prelude();
            // do, line 210
            // call mark_regions, line 210
            r_mark_regions();
            // backwards, line 211
            limit_backward = cursor;
            cursor = limit;
            // (, line 211
            // do, line 213
            int v_5 = limit - cursor;
            // call Step_1a, line 213
            r_Step_1a();
            cursor = limit - v_5;
            // or, line 215
            lab4:
            {
                int v_6 = limit - cursor;
                lab5:
                {
                    // call exception2, line 215
                    if (!r_exception2()) {
                        break lab5;
                    }
                    break lab4;
                }
                cursor = limit - v_6;
                // (, line 215
                // do, line 217
                int v_7 = limit - cursor;
                // call Step_1b, line 217
                r_Step_1b();
                cursor = limit - v_7;
                // do, line 218
                int v_8 = limit - cursor;
                // call Step_1c, line 218
                r_Step_1c();
                cursor = limit - v_8;
                // do, line 220
                int v_9 = limit - cursor;
                // call Step_2, line 220
                r_Step_2();
                cursor = limit - v_9;
                // do, line 221
                int v_10 = limit - cursor;
                // call Step_3, line 221
                r_Step_3();
                cursor = limit - v_10;
                // do, line 222
                int v_11 = limit - cursor;
                // call Step_4, line 222
                r_Step_4();
                cursor = limit - v_11;
                // do, line 224
                int v_12 = limit - cursor;
                // call Step_5, line 224
                r_Step_5();
                cursor = limit - v_12;
            }
            cursor = limit_backward;
            // do, line 227
            int v_13 = cursor;
            // call postlude, line 227
            r_postlude();
            cursor = v_13;
        }
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof EnglishStemmer;
    }
    
    @Override
    public int hashCode() {
        return EnglishStemmer.class.getName().hashCode();
    }
    
    
}

