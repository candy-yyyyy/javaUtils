import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ParkingSystem {
    class MyPair {
        TreeNode node;
        int index;

        MyPair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public boolean isEvenOddTree(TreeNode root) {
        Queue<MyPair> queue = new ArrayDeque<>();
        queue.add(new MyPair(root, 0));
        int pre = Integer.MIN_VALUE;
        int curindex = -1;
        while (!queue.isEmpty()) {
            MyPair tmp = queue.poll();
            TreeNode tmpnode = tmp.node;
            int tmpindex = tmp.index;
            if (tmpnode == null) continue;
            //换层
            if (tmpindex != curindex) {
                if (tmpindex % 2 == 0) {
                    pre = Integer.MIN_VALUE;
                } else {
                    pre = Integer.MAX_VALUE;
                }
                curindex = tmpindex;
            }

            if (curindex % 2 == 0) {
                //偶数层：奇数递增
                if (tmpnode.val % 2 == 0 || tmpnode.val <= pre) return false;
            } else if (curindex % 2 != 0) {
                //奇数层：偶数递减
                if (tmpnode.val % 2 != 0 || tmpnode.val >= pre) return false;
            }
            pre = tmpnode.val;
            queue.add(new MyPair(tmpnode.left, tmpindex + 1));
            queue.add(new MyPair(tmpnode.right, tmpindex + 1));
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = {372943, 3955002, 3966366, 4482897, 5469362, 5592545, 5665997, 5986506, 6442701, 6751933, 9008337, 9346551, 11696513, 13262545, 14316629, 17093321, 18511723, 20480038, 20843442, 22169660, 23555585, 24124001, 25263462, 26922369, 28386944, 29661750, 31359520, 32018294, 33704230, 34188085, 34420228, 35223356, 35322920, 35577946, 36955757, 39070707, 41119391, 42426181, 42656111, 44007634, 44878119, 45587657, 45764928, 46898868, 47447978, 49137400, 49375220, 49838194, 49974152, 50370133, 50384020, 50686959, 52795300, 55466951, 56866859, 57242521, 58211199, 60947408, 61133320, 61531449, 64380227, 65666017, 66025072, 66382871, 67302587, 68113496, 69266413, 69457259, 69515537, 69566327, 70036267, 71871614, 74479627, 74602314, 74865554, 75512169, 78270063, 82281228, 82682023, 83463760, 84094966, 84774083, 86099828, 86100975, 86279067, 88949265, 90752490, 90762356, 90871218, 91985983, 92607168, 94042724, 94945398, 95245464, 95390672, 95916559, 96524336, 100895088, 103413076, 103813474, 105824469, 108786686, 109688561, 110215602, 110808648, 112570998, 112658976, 117195589, 117231224, 117498810, 125095792, 128657392, 132161383, 132662707, 133016519, 133556403, 135121337, 135458691, 136913448, 138876931, 140421866, 141643405, 141752926, 142448335, 143860306, 143951255, 144775272, 146226360, 147355951, 148557703, 150732364, 150736523, 151255219, 153308145, 153548971, 155092890, 155695032, 157535993, 158234401, 158508555, 158686045, 158758341, 159762468, 160530788, 161475254, 162246928, 162413692, 164240911, 164599799, 164745328, 165011801, 166870407, 169419488, 171375769, 171766973, 172545411, 172783434, 173970843, 173976111, 176337990, 178300555, 179299106, 181599445, 182838684, 185456124, 185735703, 187729482, 188541111, 188912962, 189855067, 190480038, 193376196, 193919918, 195538861, 196268610, 196394836, 196432980, 197721040, 201233729, 202012322, 202590711, 202791423, 202917285, 203360499, 203973219, 204641242, 206064622, 206493806, 207597207, 208806719, 213317717, 213999603, 216176842, 216659522, 216777455, 216786386, 217235383, 217913623, 220960647, 224852114, 225181421, 227539179, 229065644, 230053700, 231330770, 231423035, 232940560, 235387288, 235526524, 235807579, 236141306, 236264863, 237069752, 237953962, 238750411, 238925768, 243816803, 244946757, 247106054, 247459705, 247929469, 249536538, 250253715, 250879070, 252003396, 252301853, 255065038, 257110655, 259308861, 259468951, 259558872, 260291667, 260704351, 261106615, 261253895, 261406673, 261642491, 261845194, 266119297, 266200993, 268210009, 272692424, 272887557, 274033815, 274167380, 276079491, 276714013, 277985332, 278533281, 278919368, 278945955, 279017378, 280267946, 280618841, 281217588, 282506464, 283948220, 285542963, 285990990, 285993404, 287004999, 291443103, 292528423, 292886431, 293863252, 294098782, 297749840, 297813735, 298634256, 300086531, 300337714, 302780904, 305672799, 305754611, 306866007, 307611684, 308882848, 309437645, 311023011, 311561572, 313723789, 313826099, 315256879, 315356464, 315810564, 318695009, 320269397, 320471247, 321626797, 321692779, 324365407, 327128678, 330994050, 332962583, 334743295, 335024532, 337677068, 338029868, 339082125, 340799365, 342152105, 342493106, 342865610, 342993304, 343354742, 346059486, 346472745, 347031747, 347703375, 348719183, 349100341, 349629588, 350438043, 351682711, 353340946, 353688460, 353731270, 355439839, 357042671, 358842627, 363244267, 364852329, 368553639, 369117238, 370469747, 370531904, 370985655, 371111022, 372445188, 372636280, 373050018, 374281887, 374542898, 374838167, 376510043, 378079136, 378260637, 380202735, 380304438, 380579140, 384945060, 385641363, 386430034, 386545585, 387454459, 387907883, 389335958, 392078844, 392248611, 393532496, 393970597, 396607139, 398060096, 398753558, 398813048, 399022636, 400799280, 400847732, 401238730, 401834800, 402644576, 403549170, 404217951, 406208866, 408026677, 408428258, 408472887, 408546988, 411897282, 413131574, 414569047, 417404660, 417502207, 419016438, 420326360, 421314796, 423489690, 426589937, 428339432, 428757462, 429278818, 429852996, 430847759, 431623411, 432542024, 434333544, 434608884, 435175309, 436417443, 437418510, 437741290, 438493121, 441722555, 443286775, 444131200, 444377657, 445251582, 445350989, 446640032, 449237517, 450422321, 450621342, 451650983, 451670662, 452346318, 453347077, 453783515, 453853543, 454724295, 456067292, 461297705, 465768814, 466564624, 467185661, 467244673, 469636054, 470105299, 470288755, 471395370, 471456552, 471925563, 472352163, 472756463, 472799927, 477869549, 478837128, 479493989, 479963558, 480210642, 480681689, 480776770, 481027688, 481054394, 484170945, 484335907, 485226351, 485668346, 486399671, 487643399, 495871277, 496238345, 500831844, 500859337, 501046264, 503769731, 505699169, 508993276, 509800732, 511390762, 513810575, 514457995, 514663837, 514675439, 515042971, 517108237, 517730977, 518191546, 519180615, 520313774, 521101393, 522662191, 522678362, 522947976, 525256179, 527363915, 528025645, 528995773, 530671900, 531499696, 531771596, 534332667, 534778963, 536480788, 537232226, 538647484, 544525200, 545137519, 545825551, 546568521, 546795737, 547497030, 548050074, 550701251, 551550100, 551644892, 552388547, 552794789, 554666472, 554709468, 555583699, 556401053, 558241448, 559020233, 561106792, 566842401, 569443166, 569467490, 569695743, 570027276, 570369187, 573920942, 573991243, 574306353, 575147057, 577013410, 577620114, 578536468, 583263054, 583530987, 585639861, 585940939, 586330346, 586775103, 590146907, 591598746, 592367259, 592811443, 594086867, 594732732, 594848717, 595917752, 596264596, 598408278, 598784059, 598916999, 599650245, 599719808, 600128990, 601834477, 603459392, 604042975, 605655101, 606271239, 610761849, 612491838, 612891638, 614136571, 614147433, 614870750, 615680668, 615897914, 616366632, 616416647, 620547146, 620662369, 621185034, 623259116, 623362333, 626065873, 627239019, 628763300, 629181962, 629525432, 629578214, 630125146, 630749956, 633739867, 634049981, 640023028, 640094596, 641964625, 644298057, 644974795, 646160193, 646571552, 646922033, 647032425, 648075936, 649884379, 649897167, 650735993, 651681505, 652032723, 652419231, 652519391, 655019216, 655637307, 656036529, 664446481, 665098061, 667625978, 667665041, 668552307, 669686444, 671553710, 672827218, 672947462, 673781741, 674409783, 675197849, 677941570, 678412223, 678508389, 679998318, 680164716, 680636710, 681530926, 682018974, 684488146, 685799320, 687321618, 688626324, 688789698, 690073301, 693549837, 697949957, 698940710, 699904867, 701242603, 701406856, 702327213, 702860573, 702979945, 703627005, 703974004, 704633274, 705308829, 708792155, 709768000, 709885853, 711651808, 712699380, 713883625, 715795069, 717919054, 719512551, 719845400, 719846545, 721223197, 722184823, 722485907, 722986020, 723538236, 725307510, 727170339, 727700748, 729631139, 730528788, 730587681, 731217074, 731990166, 732176738, 733111806, 733232684, 734373379, 735152127, 737368686, 737763395, 738271388, 738475745, 738788914, 739651460, 741280469, 741367854, 743052520, 743392802, 744174323, 744656343, 745463926, 746113397, 746897967, 747043856, 747231369, 747359619, 748103392, 748189848, 749348003, 751843130, 754004275, 755453276, 756537205, 757991903, 758096803, 758260067, 758296760, 758911481, 759315104, 759609818, 762454654, 763041078, 763049214, 763187625, 764730157, 766169692, 766875266, 767171820, 768339826, 769170950, 769570767, 771462758, 772414792, 773559574, 773646545, 774331986, 775545537, 776687729, 776767430, 776916574, 777241016, 777316883, 777521693, 779833108, 781233392, 782471557, 787371387, 788912902, 789324232, 790311285, 790529333, 790627901, 790713588, 791536071, 793238259, 794289127, 794592123, 795203995, 795431601, 796527120, 798343341, 798929978, 799055066, 800222387, 801045214, 803248048, 803512270, 803520773, 805218198, 805647234, 810575971, 811766393, 813419643, 814097441, 814190414, 814245301, 815090406, 815571786, 815749517, 820935023, 821726650, 821853647, 821985707, 823202881, 824162584, 824392620, 825276012, 827125285, 827476198, 829174774, 829553641, 830537253, 831755887, 832864062, 833360269, 833985161, 834514736, 834765352, 834967936, 835079248, 835819845, 836099355, 839637811, 840754258, 842882903, 845520025, 845970246, 847384457, 847673285, 848114860, 848188952, 850574796, 851172776, 851227137, 851653228, 853146749, 856191259, 857052010, 857927054, 858172550, 861515968, 861637673, 862260144, 863194641, 866381742, 868417518, 871455718, 874752932, 875014371, 875758118, 877094137, 878914268, 879824325, 881492167, 885167308, 885644075, 885756208, 886036826, 889105349, 890423746, 890464395, 890794846, 891213001, 891509597, 893125467, 893552499, 894532288, 894676103, 895095727, 895270606, 896159449, 898172708, 898449967, 899600778, 901154870, 903525388, 903806566, 904495587, 906724193, 907253734, 907473739, 907495205, 907546865, 909620982, 910819662, 911150279, 913802189, 914151334, 915363558, 915843681, 916355644, 918334129, 918414426, 921923156, 922012962, 923640079, 924012986, 924070603, 924470060, 925612393, 925996232, 926609019, 926804010, 927108035, 927227510, 927510989, 929756587, 930540829, 932118779, 932302014, 933786312, 934265299, 936066347, 936272937, 936556685, 937893576, 941613956, 941656019, 942542079, 943869935, 947056157, 949811919, 950015340, 950539295, 951380527, 951702172, 955815650, 956730609, 957163779, 959034073, 961573216, 962939541, 963485166, 963591327, 964293353, 965948357, 966938161, 968818288, 970472763, 973769604, 975046280, 977101316, 979034407, 980187078, 980359133, 980838578, 986756352, 989880662, 990624830, 991096780, 991889343, 992071073, 994942836, 995121378, 995818601, 995927038, 996166201, 997834654, 998212640, 998649302};
        String str = "opswcvuzsvzocinrzihzcfwomdgjsqnzjrbxkdwdjnomflzkbzcbyfipbtgumioioluyrhifyzpqoozuzqvaeodmtfyeokegyixhpbgnnntediluopjiccspjxggfpwpfaaussyyihsrxfzxrvcpvyelzzlidxoftrmjaeocyziaackeozjwpcztanpeiqpvpzerchqteautqpyebxcbpyfxptoocdubwgaghbnakybecvabhcrhjomfhunyuudqsfrvdfntoaptyjbpmxetiiwqdjkejunsnvyyvbwtgiquixwhpiagcakmthhjasmekyvozbcxurwoxlhdiqofdyauhhxayfpahxsjuklqcyeczmslwywmvxvdgjvqllipvggoramesoqmbiahulawmvabbbpezskksoftrlxzfehyxyjqtkkwtqeqnjysrvnfqykofkojubueybiyesholscrcvqnxtmxiiehlmcyzasksjpgeqdsmhjjffyvlslubbujouvoeqozttvvnsokrodrwlxyerzevqzemnxuvctuhoojcipnkvabkpbggcqezlnukdieoweptnwvedwzmrhjghaxqybnwaqdskjbzmhtkgzmrmlckerfhkrigdqksiewuzonrqoxsnwfbwkseaszislvmtllzhpqrkyarvuojeahulsxlsbqezkunpbempyvhukyppluhnpnwrhzgkxdrfqzhqemfaspfagjheqorzgpcgolpxnnfukgqfrddjalosakqzgigwalabrwvyvxvyjcefjspjxgwlrzqogotxiciabrnoniorvwjzntwxzxotnfgnpskqekhbgvalpivkbqpcimgqxliutaatpmaimuctubhaqwliwmigkseg";
        System.out.println(new ParkingSystem().slowestKey(arr, str));
    }

    // 1614. 括号的最大嵌套深度
    public int maxDepth(String s) {
        String[] arr = s.split("");
        int max = 0;
        int index = 0;
        for (String str : arr) {
            if ("(".equals(str)) {
                index++;
            } else if (")".equals(str)) {
                index--;
            }
            if (index > max) {
                max = index;
            }
        }
        return max;
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] connected = new boolean[n][n];
        int[] cnt = new int[n];
        for (int[] road : roads) {
            cnt[road[0]]++;
            cnt[road[1]]++;
            connected[road[0]][road[1]] = true;
            connected[road[1]][road[0]] = true;
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int t = cnt[i] + cnt[j];
                if (connected[i][j]) {
                    t--;
                }
                max = Math.max(max, t);
            }
        }
        return max;
    }

    public static double trimMean(int[] arr) {
        Arrays.sort(arr);
        int length = arr.length;
        int index = (int) (length * 0.05);
        int max = 0;
        int min = 0;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum = sum + arr[i];
            if (i < index) {
                min = min + arr[i];
            }
            if (i >= length - index) {
                max = max + arr[i];
            }
        }

        return (double) (sum - (min + max)) / (length - 2 * index);
    }

    public int maxLengthBetweenEqualCharacters(String s) {
        String[] arr = s.split("");
        Map<String, Object> map = new HashMap<>();
        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                int index = (int) map.get(arr[i]);
                index = i - index;
                max = Math.max(max, index);
            } else {
                map.put(arr[i], i + 1);
            }
        }
        return max;
    }

    public boolean backspaceCompare(String S, String T) {
        String[] arr_1 = S.split("");
        String[] arr_2 = T.split("");
        String str_1 = "";
        String str_2 = "";
        for (int i = 0; i < arr_1.length; i++) {
            if ("#".equals(arr_1[i]) && str_1.length() > 0) {

                str_1 = str_1.substring(0, str_1.length() - 1);
            } else if ("#".equals(arr_1[i])) {
                continue;
            } else {
                str_1 = str_1 + arr_1[i];
            }
        }
        for (int i = 0; i < arr_2.length; i++) {
            if ("#".equals(arr_2[i]) && str_2.length() > 0) {

                str_2 = str_2.substring(0, str_2.length() - 1);
            } else if ("#".equals(arr_2[i])) {
                continue;
            } else {
                str_2 = str_2 + arr_2[i];
            }
        }
        if (str_1.equals(str_2)) {
            return true;
        } else {
            return false;
        }
    }

    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode newHead = head;
        while (newHead != null) {
            list.add(newHead);
            newHead = newHead.next;
        }
        int index = 1;
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                head.next = null;
                return;
            }
            if (i % 2 == 0) {
                head.next = list.get(list.size() - index);
                index++;
            } else {
                System.out.println(list.get(i).val);
                head.next = list.get(i);
            }
            System.out.println(head.val + "==" + head.next.val);
            head = head.next;

        }
    }

    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int max = 0;
        char word = 0;
        for (int i = 0; i < releaseTimes.length; i++) {
            int time = 0;
            if (i == 0) {
                time = releaseTimes[i];
            } else {
                time = releaseTimes[i] - releaseTimes[i - 1];
            }
            if (time > max) {
                word = keysPressed.charAt(i);
            } else if (time == max && keysPressed.charAt(i) > word) {
                word = keysPressed.charAt(i);
            }
            max = Math.max(time, max);
        }
        return word;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int begin = l[i];
            int end = r[i];
            int newArrLength = end - begin + 1;
            int[] newArr = new int[newArrLength];
            System.arraycopy(nums, begin, newArr, 0, newArrLength);
            Arrays.sort(newArr);
            int diff = 0;
            Boolean flag = Boolean.valueOf(true);
            for (int j = 1; j < newArr.length; j++) {
                if (j == 1) {
                    diff = newArr[1] - newArr[0];
                    continue;
                }
                int cnt = newArr[j] - newArr[j - 1];
                if (cnt != diff) {
                    flag = Boolean.valueOf(false);
                    break;
                }
            }
            list.add(flag);
        }
        return list;
    }

    public int longestMountain(int[] A) {
        int max = 0;
        Boolean flag1 = Boolean.valueOf(false);
        Boolean flag2 = Boolean.valueOf(false);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                if (flag2) {
                    flag2 = Boolean.valueOf(false);
                    list.add(max);
                    max = 0;
                }
                flag1 = Boolean.valueOf(true);
                if (max == 0) {
                    max = 1;
                }
                max++;
            }
            if (flag1 && A[i] < A[i - 1]) {
                flag2 = Boolean.valueOf(true);
                max++;
            }
            if (A[i] == A[i - 1]) {
                flag1 = Boolean.valueOf(false);
                max = 0;
            }
            if (i == A.length - 1 && flag1 && flag2) {
                list.add(max);
            }
        }
        int longest = 0;
        for (int i = 0; i < list.size(); i++) {
            longest = Math.max(list.get(i), longest);
        }
        return longest;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        getVal(root, list);
        return list;
    }

    public void getVal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        getVal(root.left, list);
        getVal(root.right, list);

    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }

        List<Integer> list = new ArrayList<>(map.values());
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) == list.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[] frequencySort(int[] nums) {
        int[] newInt = new int[nums.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        return null;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    set.add(nums1[i]);
                }
            }
        }
        int[] arr = new int[set.size()];
        int index = 0;
        for (Integer integer : set) {
            arr[index] = integer;
            index++;
        }
        return arr;

    }

    public boolean validMountainArray(int[] A) {
        int index = 0;
        Boolean flag = false;
        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1]) {
                return false;
            }
            if (flag && A[i] > A[i - 1]) {
                return false;
            }

            if (A[i] < A[i - 1]) {
                if (i == 1) {
                    return false;
                }
                flag = true;
            }
        }

        return flag;
    }

    public int[] sortByBits(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int[] arr1 = new int[10001];
        for (int i = 0; i < arr.length; i++) {
            arr1[arr[i]] = getBinary(arr[i]);
            list.add(arr[i]);
        }
        System.out.println(list);
        Integer index = new Integer(1);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1 + "==" + o2);
                if (arr1[o1] != arr1[o2]) {
                    return arr1[o1] - arr1[o2];
                } else {
                    return o1 - o1;
                }
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private int getBinary(int num) {
        int count = 0;
        while (num != 0) {
            if (num % 2 == 1) {
                count++;
            }
            num = num / 2;
        }
        return count;
    }

    public int[][] kClosest(int[][] points, int K) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            list.add(points[0]);
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                System.out.println(Arrays.toString(o1) + "===" + Arrays.toString(o2));
                return (int) ((Math.pow(o1[0], 2) + Math.pow(o1[1], 2)) - (Math.pow(o2[0], 2) + Math.pow(o2[1], 2)));
            }
        });
        int[][] arr = new int[K][2];
        for (int i = 0; i < K; i++) {
            arr[i][0] = list.get(i)[0];
            arr[i][1] = list.get(i)[1];
        }
        return arr;
    }

    public int[] sortArrayByParityII(int[] A) {
        int halfSize = A.length / 2;
        int[] evenNum = new int[halfSize];
        int[] oddNum = new int[halfSize];
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                evenNum[i / 2] = A[i];
            } else {
                oddNum[i / 2] = A[i];
            }
        }
        System.out.println(Arrays.toString(evenNum));
        System.out.println(Arrays.toString(oddNum));
        int[] newArr = new int[A.length];
        for (int i = 0; i < newArr.length; i++) {
            if (i % 2 == 0) {
                newArr[i] = evenNum[i / 2];
            } else {
                newArr[i] = oddNum[i / 2];
            }
        }
        return newArr;
    }

    // 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
    public String removeKdigits(String num, int k) {
        // create an increasing stack 后面比前面一位 后面比前面小出栈 新的数据再入栈
        Stack<Character> stack = new Stack<>();
        for (char ch : num.toCharArray()) {
            while (!stack.isEmpty() && ch < stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(ch);
        }

        // clear the last biggest elements 如果最后还有位数没有处理完成 则最后剩多少出栈多少
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        // transfer stack to string
        StringBuilder ans = new StringBuilder();
        boolean leadingZero = true;
        for (char ch : stack) {
            // 处理前面位为0的情况
            if (leadingZero && ch == '0') {
                continue;
            }
            leadingZero = false;
            ans.append(ch);
        }
        return ans.length() == 0 ? "0" : ans.toString();
    }

    // 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[][] reconstructQueue(int[][] people) {
        // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
        // 再一个一个插入。
        // [7,0]
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        LinkedList<int[]> list = new LinkedList<>();
        for (int[] i : people) {
            list.add(i[1], i);
        }

        return list.toArray(new int[list.size()][2]);
    }

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] arr = new int[R * C][2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int t = i * C + j;
                arr[t][0] = i;
                arr[t][1] = j;
            }
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int a = dist(o2[0], o2[1], r0, c0);
                int b = dist(o1[0], o1[1], r0, c0);
                return a - b;
            }
        });
        return arr;
    }

    private int dist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] == 0) {
                    nums[j] = nums[j + 1];
                    nums[j + 1] = 0;
                } else if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
            System.out.println(Arrays.toString(nums));
        }
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode returnNode = new ListNode(0);
        ListNode curr = head.next;
        ListNode lastSorted = head;
        while (curr != null) {
            if (curr.val < lastSorted.val) {
                int temp = curr.val;
                curr.val = lastSorted.val;
                lastSorted.val = temp;
            }
        }
        return returnNode.next;
    }

    // 冒泡排序
    public void maopaoSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    // 插入排序
    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
                j--;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public boolean isAnagram(String s, String t) {
        int count[] = new int[26];
        // 把出现的字符对应的数组索引记录次数
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        // 把t里面出现的字符对应的出现次数减掉之前的出现次数
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'a']--;
        }
        // 当每一个字符最后计算的数组的值为0时才说明s与t时相同的字母异位词
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public int findMinArrowShots(int[][] points) {
        //边界条件判断
        if (points == null || points.length == 0)
            return 0;
        //按照每个气球的右边界排序
        Arrays.sort(points, (a, b) -> a[1] > b[1] ? 1 : -1);
        //获取排序后第一个气球右边界的位置，我们可以认为是箭射入的位置
        int last = points[0][1];
        //统计箭的数量
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            //如果箭射入的位置小于下标为i这个气球的左边位置，说明这支箭不能
            //击爆下标为i的这个气球，需要再拿出一支箭，并且要更新这支箭射入的
            //位置
            if (last < points[i][0]) {
                last = points[i][1];
                count++;
            }
        }
        return count;
    }

    // 上升下降字符串
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public String sortString(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        StringBuffer sb = new StringBuffer();
        while (sb.length() < s.length()) {
            // 正序排
            for (int i = 0; i < 26; i++) {
                if (arr[i] > 0) {
                    sb.append((char) (i + 'a'));
                    arr[i]--;
                }

            }
            // 倒序排
            for (int i = 25; i >= 0; i--) {
                if (arr[i] > 0) {
                    sb.append((char) (i + 'a'));
                    arr[i]--;
                }

            }
        }
        return sb.toString();
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                Integer sumAB = A[i] + B[j];
                if (map.containsKey(sumAB)) {
                    map.put(sumAB, map.get(sumAB) + 1);
                } else {
                    map.put(sumAB, 1);
                }
            }
        }
        System.out.println(map);
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                Integer sumCD = C[i] + D[j];
                if (map.containsKey(-sumCD)) {
                    count += map.get(-sumCD);
                }
            }
        }
        return count;
    }

    public int reversePairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            System.out.println("i====" + i);
            for (int j = i + 1; j < nums.length; j++) {
                System.out.println(j);
                if ((long) nums[i] > 2 * (long) nums[j]) {
                    System.out.println("1" + nums[i] + "===" + nums[j]);
                    count++;
                }
            }
        }
        return count;
    }

    /*5557. 最大重复子字符串
            给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。

            给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
    */
    public int maxRepeating(String sequence, String word) {
        int maxCount = 0;
        int count = 0;
        int wordLength = word.length();
        boolean flag = false;
        while (sequence.indexOf(word) != -1) {
            // 不连续
            if (sequence.indexOf(word) != 0) {
                count = 0;
            }
            count++;
            sequence = sequence.substring(sequence.indexOf(word) + wordLength, sequence.length());
            maxCount = Math.max(count, maxCount);
        }
        return count;
    }

    /*5558. 合并两个链表
            给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。

            请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。
    */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        int index = 0;
        ListNode reListNode = new ListNode(0);
        ListNode node = reListNode;
        boolean flag = true;
        while (list1 != null) {
            // 当循环到开始替换节点
            if (index == a) {
                reListNode.next = list2;
                flag = false;
            }
            // 当循环到结束替换节点
            if (index == b) {
                // list2.size > b-a
                while (reListNode != null) {
                    if (reListNode.next == null) {
                        reListNode.next = list1.next;
                        return node.next;
                    }
                    reListNode = reListNode.next;
                }
                // list2.size < b-a
                reListNode.next = list1.next;
                return node.next;
            }
            // 遍历 链表list1并且记录遍历次数
            if (flag && reListNode != null) {
                reListNode.next = list1;
            }
            list1 = list1.next;
            // 避免最后一个节点为空
            if (reListNode.next != null) {
                reListNode = reListNode.next;
            }
            index++;
        }
        return node.next;
    }

    /*
    给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i 位客户在第 j 家银行托管的资产数量。返回最富有客户所拥有的 资产总量 。
    */
    public int maximumWealth(int[][] accounts) {
        int maxWealth = 0;
        for (int i = 0; i < accounts.length; i++) {
            int sumWealth = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                sumWealth += accounts[i][j];
            }
            maxWealth = Math.max(maxWealth, sumWealth);
        }
        return maxWealth;
    }

    /*5614. 找出最具竞争力的子序列*/
    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek() > nums[i] && (stack.size() + nums.length - i) > k) {
                stack.pop();
            }
            if (stack.size() == k) {
                continue;
            }
            stack.push(nums[i]);
        }
        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

    // 976. 三角形的最大周长
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if ((A[i] - A[i - 1] < A[i - 2]) && (A[i - 2] + A[i - 1] > A[i])) {
                return A[i] + A[i - 1] + A[i - 2];
            }
        }
        return 0;
    }

    // 34. 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        int[] arr = {-1, -1};
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (flag) {
                    arr[1] = i;
                } else {
                    flag = true;
                    arr[0] = i;
                }
            }
        }
        return arr;
    }

    // 204. 计数质数 统计所有小于非负整数 n 的质数的数量。
    public int countPrimes(int n) {
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isPrime(int num) {
        int max = (int) Math.sqrt(num);
        for (int i = 2; i <= max; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 659. 分割数组为连续子序列
    public boolean isPossible(int[] nums) {
        return true;
    }

    // 118.杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> intList = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i) {
                    intList.add(1);
                    continue;
                }
                List<Integer> tempList = list.get(i - 2);
                intList.add(tempList.get(j - 1) + tempList.get(j - 2));
            }
            list.add(intList);
        }
        return list;
    }

    // 861. 翻转矩阵后的得分
    public int matrixScore(int[][] A) {
       /* for (int i=0;i<A.length;i++) {
            int indexInt = A[i][0];
            for (int j=0;j<A[i].length;j++){
                if (indexInt == 0) {
                    A[i][j] = A[i][j] == 0 ? 1 : 0;
                }
            }
        }
        for (int i=0;i<A[0].length;i++) {
            int countOne = 0;
            int countZero = 0;
            for (int j=0;j<A.length;j++) {
                if (A[j][i] == 1) {
                    countOne ++;
                } else {
                    countZero ++;
                }
            }
            if (countOne < countZero) {
                for (int j=0;j<A.length;j++) {
                    A[j][i] = A[j][i] == 0 ?1 :0;
                }
            }
        }
        // 计算
        int result = 0;
        for (int i=0;i<A.length;i++) {
            String str = "";
            for (int j=0;j<A[i].length;j++){
                str += A[i][j];
            }
            System.out.println(str);
            result += Integer.parseInt(str,2);
        }
        return result;*/
        if (A.length == 0 || A[0].length == 0) {
            return 0;
        }

        int result = A.length; // 第一位全为1

        for (int w = 1; w < A[0].length; w++) { // 从第二位开始遍历
            int count1 = 0;

            for (int h = 0; h < A.length; h++) {
                if (A[h][w] == A[h][0]) { // 1的个数
                    count1++;
                }
            }

            // 求出0的个数与1的个数的最大值，作为最终1的个数
            System.out.println(count1);
            result = (result << 1) + Math.max(count1, A.length - count1);

        }

        return result;
    }

    // 842. 将数组拆分成斐波那契序列
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        backtrack(S.toCharArray(), res, 0);
        return res;
    }

    public boolean backtrack(char[] digit, List<Integer> res, int index) {
        //边界条件判断，如果截取完了，并且res长度大于等于3，表示找到了一个组合。
        if (index == digit.length && res.size() >= 3) {
            return true;
        }
        for (int i = index; i < digit.length; i++) {
            //两位以上的数字不能以0开头
            if (digit[index] == '0' && i > index) {
                break;
            }
            //截取字符串转化为数字
            long num = subDigit(digit, index, i + 1);
            //如果截取的数字大于int的最大值，则终止截取
            if (num > Integer.MAX_VALUE) {
                break;
            }
            int size = res.size();
            //如果截取的数字大于res中前两个数字的和，说明这次截取的太大，直接终止，因为后面越截取越大
            if (size >= 2 && num > res.get(size - 1) + res.get(size - 2)) {
                break;
            }
            if (size <= 1 || num == res.get(size - 1) + res.get(size - 2)) {
                //把数字num添加到集合res中
                res.add((int) num);
                //如果找到了就直接返回
                if (backtrack(digit, res, i + 1))
                    return true;
                //如果没找到，就会走回溯这一步，然后把上一步添加到集合res中的数字给移除掉
                res.remove(res.size() - 1);
            }
        }
        return false;
    }

    //相当于截取字符串S中的子串然后转换为十进制数字
    private long subDigit(char[] digit, int start, int end) {
        long res = 0;
        for (int i = start; i < end; i++) {
            res = res * 10 + digit[i] - '0';
        }
        return res;
    }

    // 62. 不同路径
    public int uniquePaths(int m, int n) {
       /* int[][] arr = new int[m][n];
        for (int i=0;i<m;i++) {
            arr[i][0] = 1;
        }
        for (int j=0;j<n;j++) {
            arr[0][j] = 1;
        }
        for (int i=1;i<m;i++) {
            for (int j=1;j<n;j++) {
                arr[i][j] = arr[i][j-1] + arr[i-1][j];
            }
        }
        return arr[m-1][n-1];*/
        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[j] += arr[j - 1];
            }
        }
        return arr[n - 1];
    }

    // 376. 摆动序列
    public int wiggleMaxLength(int[] nums) {
        /*List<Stack<Integer>> list = new ArrayList<>();
        for (int i=0;i < nums.length; i++) {
            Stack<Integer> stack = new Stack<>();
            stack.push(nums[i]);
            int lastDiffNum = 0;
            for (int j=i+1; j < nums.length; j++) {
                int lastNum = stack.peek();
                // 当前循环数字与上一个数字相同则跳过当前
                if (nums[j] == lastNum) {
                    continue;
                }
                int diffNum = nums[j] - lastNum;
                // 正负符号相同跳过
                if (diffNum * lastDiffNum > 0 || diffNum == 0) {
                    continue;
                } else {
                    lastDiffNum = diffNum;
                    stack.push(nums[j]);
                }
            }
            if (stack.size() == nums.length) {
                return nums.length;
            }
            list.add(stack);
        }
        int maxCount = 0;
        for (int i=0; i < list.size(); i++) {
            maxCount = Math.max(maxCount, list.get(i).size());
        }
        return maxCount;*/
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int prevdiff = nums[1] - nums[0];
        int ret = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
//            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
            if (diff * prevdiff < 0) {
                ret++;
                prevdiff = diff;
            }
        }
        return ret;
    }

    // 5609. 统计一致字符串的数目
    public int countConsistentStrings(String allowed, String[] words) {
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            boolean containFlag = true;
            for (int j = 0; j < words[i].length(); j++) {
                // 未匹配到
                if (allowed.indexOf(String.valueOf(words[i].charAt(j))) == -1) {
                    containFlag = false;
                }
            }
            if (containFlag) {
                count++;
            }
        }
        return count;
    }

    // 5610. 有序数组中差绝对值之和
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int length = nums.length;
        int[] arr = new int[length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (map.containsKey(nums[i])) {
                arr[i] = map.get(nums[i]);
            } else {
                for (int j = 0; j < length; j++) {
                    arr[i] = arr[i] + Math.abs(nums[i] - nums[j]);
                }
                map.put(nums[i], arr[i]);
            }

        }
        return arr;
    }

    // 5625. 比赛中的配对次数
    public int numberOfMatches(int n) {
        int count = 0;
        while (n > 1) {
            if (n % 2 == 0) {
                count += n / 2;
                n = n / 2;
            } else {
                count = count + (n - 1) / 2;
                n = 1 + (n - 1) / 2;
            }
        }
        return count;
    }

    // 5626. 十-二进制数的最少数目
    public int minPartitions(String n) {
        int max = 0;
        for (int i = 0; i < n.length(); i++) {
            int num = Integer.valueOf(String.valueOf(n.charAt(i)));
            max = Math.max(max, num);
        }
        return max;
    }

    // 217. 存在重复元素
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            } else {
                map.put(nums[i], nums[i]);
            }
        }
        return false;
    }

    // 49. 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
       /* List<List<String>> list = new ArrayList<>();
        for (int i=0;i<strs.length;i++) {
            List<String> newList = null;
            Boolean flag = false;
            for (int j=0;j<list.size();j++) {
                newList = list.get(j);
                if (isAnagrams(newList, strs[i])) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            } else {
                newList = new ArrayList<>();
                newList.add(strs[i]);
                list.add(newList);
            }
        }
        return list;*/
        // 官方答案
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String word = new String(arr);
            List<String> list = map.getOrDefault(word, new ArrayList<>());
            list.add(str);
            map.put(word, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    private Boolean isAnagrams(List<String> list, String str) {
        String baseStr = list.get(0);
        if (baseStr.length() != str.length()) {
            return false;
        }
        int[] a = new int[26];
        int[] b = new int[26];
        for (int i = 0; i < str.length(); i++) {
            a[baseStr.charAt(i) - 'a']++;
            b[str.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (a[i] == b[i]) {
                continue;
            } else {
                return false;
            }
        }
        list.add(str);
        return true;
    }

    // 738. 单调递增的数字
    public int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }

    //  290. 单词规律
    public boolean wordPattern(String pattern, String s) {
        String[] patternArr = pattern.split("");
        String[] arr = s.split(" ");
        if (patternArr.length != arr.length) {
            return false;
        }
        Map<String, String> map = new HashMap<>();
        Map<String, String> map1 = new HashMap<>();
        for (int i = 0; i < patternArr.length; i++) {
            if ((map.containsKey(patternArr[i]) && !arr[i].equals(map.get(patternArr[i]).toString()))
                    || (map1.containsKey(arr[i]) && !patternArr[i].equals(map1.get(arr[i]).toString()))) {
                return false;
            } else {
                map.put(patternArr[i], arr[i]);
                map1.put(arr[i], patternArr[i]);
            }
        }
        return true;
    }

    // 389. 找不同
    public char findTheDifference(String s, String t) {
        int[] sArr = new int[26];
        int[] tArr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sArr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            tArr[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sArr[i] != tArr[i]) {
                return (char) ('a' + i);
            }
        }
        return ' ';
    }

    // 48. 旋转图像
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = arr[i][j];
            }
        }
    }

    // 5629. 重新格式化电话号码
    public String reformatNumber(String number) {
        number = number.replaceAll(" ", "").replaceAll("-", "");
        if (number.length() <= 3) {
            return number;
        }
        String str = "";
        int n = number.length() / 3;
        int m = number.length() % 3;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                if (m == 1) {
                    m++;
                    str += number.substring(3 * i, 3 * i + 2);
                } else {
                    str += number.substring(3 * i, 3 * i + 3);
                }
            } else {
                str += number.substring(3 * i, 3 * i + 3) + "-";
            }
            if (i == n - 1 && m > 0) {
                str += "-" + number.substring(number.length() - m, number.length());
            }
        }
        return str;
    }

    // 746. 使用最小花费爬楼梯
    public int minCostClimbingStairs(int[] cost) {
       /* int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];*/
        int len = cost.length;
        for (int i = 2; i < len; i++) {
            cost[i] += Math.min(cost[i - 1], cost[i - 2]);
        }
        return Math.min(cost[len - 1], cost[len - 2]);
    }

    // 103. 二叉树的锯齿形层序遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        test(root, list, 0);
        return list;
    }

    private void test(TreeNode root, List<List<Integer>> list, int level) {
        if (root != null) {
            int n = level % 2;
            if (list.get(level) == null) {
                list.add(new ArrayList<Integer>());
            }
            // 判断从左开始还是从右开始
            if (n == 0) {
                // 从左到右
                list.get(level).add(0, root.val);
            } else {
                // 从右到左
                list.get(level).add(root.val);
            }
            test(root.left, list, level + 1);
            test(root.right, list, level + 1);
        }
    }

    // 455. 分发饼干
    public int findContentChildren(int[] g, int[] s) {
        // 先按顺序排序
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int j = 0;
        for (int i = 0; i < s.length && j < g.length; i++) {
            if (g[j] <= s[i]) {
                j++;
                count++;
            }
        }
        return count;

    }

    // 5621. 无法吃午餐的学生数量
    public int countStudents(int[] students, int[] sandwiches) {
        int index = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < students.length; i++) {
            list.add(students[i]);
        }
        int returnCount = 0;
        while (true) {
            if (list.get(index) == sandwiches[index]) {
                index++;
                returnCount = 0;
            } else {
                int num = list.get(index);
                list.remove(index);
                list.add(num);
                returnCount++;
            }
            if (returnCount == list.size() - index) {
                break;
            }
        }
        return list.size() - index;
    }

    // 5637. 判断字符串的两半是否相似
    public boolean halvesAreAlike(String s) {
        char[] arr = s.toCharArray();
        int count = 0;
        int length = arr.length;
        for (int i = 0; i < length / 2; i++) {
            char m = arr[i];
            char n = arr[length - i - 1];
            if (m == 'a' || m == 'e' || m == 'i' || m == 'u' || m == 'o' || m == 'A' || m == 'E' || m == 'I' || m == 'O' || m == 'U') {
                count++;
            }
            if (n == 'a' || n == 'e' || n == 'i' || n == 'u' || n == 'o' || n == 'A' || n == 'E' || n == 'I' || n == 'O' || n == 'U') {
                count--;
            }
        }
        return count == 0 ? true : false;
    }

    // 205. 同构字符串
    public boolean isIsomorphic(String s, String t) {

        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            char y = t.charAt(i);
            if ((map1.containsKey(x) && map1.get(x) != y) || (map2.containsKey(y) && map2.get(y) != x)) {
                return false;
            }
            map1.put(x, y);
            map2.put(y, x);
        }
        return true;
    }

    // 605. 种花问题
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                n--;
                flowerbed[i] = 1;
            }
        }
        return n <= 0;
    }

    // 509. 斐波那契数
    public int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        for (int i = 0; i <= n; i++) {
            list.add(list.get(i - 1) + list.get(i - 2));
        }
        return list.get(n);
    }

    // 830. 较大分组的位置
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> list = new ArrayList<>();
        int m = s.length();
        int n = 1;
        for (int i = 0; i < m; i++) {
            if (i + 1 == m || s.charAt(i) != s.charAt(i + 1)) {
                if (n >= 3) {
                    list.add(Arrays.asList(i - n + 1, i));
                }
                n = 1;

            } else {
                n++;
            }
        }
        return list;
    }

    // 5633. 计算力扣银行的钱
    public int totalMoney(int n) {
        int m = n / 7;
        int remainder = n % 7;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 7; j++) {
                count = count + i + (j + 1);
            }
        }
        for (int i = 0; i < remainder; i++) {
            count = count + (i + 1) + m;
        }
        return count;
    }

    // 5649. 解码异或后的数组
    public int[] decode(int[] encoded, int first) {
        int[] arr = new int[encoded.length + 1];
        arr[0] = first;
        for (int i = 1; i < arr.length - 1; i++) {
            arr[i] = arr[i - 1] ^ encoded[i - 1];
        }
        return arr;
    }

    // 228. 汇总区间
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int i = 0;

        while (i < nums.length) {
            int start = i;
            i++;
            while (i < nums.length && nums[i - 1] + 1 == nums[i]) {
                i++;
            }
            int end = i - 1;
            if (start == end) {
                list.add(String.valueOf(nums[start]));
            } else {
                list.add(nums[start] + "->" + nums[end]);
            }

        }
        return list;
    }

    // 1486. 数组异或操作
    public int xorOperation(int n, int start) {
        int x = start;
        for (int i = 1; i < n; i++) {
            x = x ^ (start + 2 * i);
        }
        return x;
    }

    // 421. 数组中两个数的最大异或值
    public int findMaximumXOR(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i; j < nums.length; j++) {
                int n = nums[i] ^ nums[j];
                max = Math.max(max, n);
            }
        }
        return max;
    }

    // 993. 二叉树的堂兄弟节点
   /* public boolean isCousins(TreeNode root, int x, int y) {

        while (root != null) {
            int left = root.left.val;
            int right = root.right.val;
        }
    }*/

    //    754. 长度为三且各字符不同的子字符串
    public int countGoodSubstrings(String s) {
        if (s.length() <= 2) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) != s.charAt(i + 1) && s.charAt(i) != s.charAt(i + 2) && s.charAt(i + 1) != s.charAt(i + 2)) {
                n++;
            }
        }
        return n;
    }

    // 5755. 数组中最大数对和的最小值
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int mix = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            mix = Math.max(mix, nums[i] + nums[nums.length - i - 1]);
        }
        return mix;
    }

    //    5772. 检查某单词是否等于两单词之和
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int a = sumWord(firstWord);
        int b = sumWord(secondWord);
        int c = sumWord(targetWord);
        if (a + b == c) {
            return true;
        } else {
            return false;
        }
    }

    public int sumWord(String str) {
        char[] s = str.toCharArray();
        String sum = "";
        for (int i = 0; i < s.length; i++) {
            sum += Integer.valueOf(s[i]) - 97;
        }
        return Integer.valueOf(sum);
    }

    //    5773. 插入后的最大值
    public String maxValue(String n, int x) {
        String str = "";
        Boolean flag = true;
        if (n.contains("-")) {
            str += "-";
            n = n.replace("-", "");
            String[] arr = n.split("");
            List<String> list = new ArrayList<>(Arrays.asList(arr));
            for (int i = 0; i < list.size(); i++) {
                if (x < Integer.parseInt(list.get(i)) && flag) {
                    str += String.valueOf(x);
                    flag = false;
                }
                str += list.get(i);
            }
        } else {
            String[] arr = n.split("");
            List<String> list = new ArrayList<>(Arrays.asList(arr));
            for (int i = 0; i < list.size(); i++) {
                if (x > Integer.parseInt(list.get(i)) && flag) {
                    str += String.valueOf(x);
                    flag = false;
                }
                str += list.get(i);
            }
        }
        if (flag) {
            str += String.valueOf(x);
        }
        return str;
    }

    // 231. 2 的幂
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 0) {
            if (n % 2 != 0 && n / 2 != 0) {
                return false;
            }
            n /= 2;
        }
        return true;
    }

    // 203. 移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        ListNode newNode = new ListNode(-1);
        addNewListNode(head, newNode, val);
        return newNode.next;
    }

    // 递归 新增一个新的链表保存数据
    private void addNewListNode(ListNode head, ListNode newListNode, int val) {
        if (head != null) {
            if (head.val != val) {
                // 节点的值不等于排除值val时 写入新节点
                newListNode.next = new ListNode(head.val);
            }
            // newListNode的next节点为空时表示当前递归并未赋值，因此传入的是当前节点
            addNewListNode(head.next, newListNode.next == null ? newListNode : newListNode.next, val);
        }
    }

    // 494. 目标和
    int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        equalsAddTarget(0, target, 0, nums);
        return count;
    }

    private void equalsAddTarget(int sum, int target, int index, int[] nums) {
        if (index == nums.length && sum == target) {
            count++;
        } else {
            equalsAddTarget(sum + nums[index], target, index + 1, nums);
            equalsAddTarget(sum - nums[index], target, index + 1, nums);
        }
    }

    // 168
    public String convertToTitle(int columnNumber) {
        String str = "";
        int a = columnNumber;
        // 循环26进制
        while (a > 26) {
            a = a / 26;
            str = String.valueOf((char) (64 + a)) + str;
        }
        //
        int b = columnNumber % 26;
        if (b > 0) {
            str = str + String.valueOf((char) (64 + b));
        }

        return str;
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        int half = (length + 1) / 2;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                int k = map.get(num).intValue();
                k++;
                map.put(num, k);
                if (k >= half) {
                    return num;
                }
            } else {
                map.put(num, 1);
            }
        }
        return -1;
    }

    // 5792
    public int countTriples(int n) {
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                double x = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));
                double y = x % 1;
                if (y == 0 && x <= n) {
                    max++;
                }
            }
        }
        return max;
    }

    // 5808
    public int[] getConcatenation(int[] nums) {
        List<Integer> list1 = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums).boxed().collect(Collectors.toList());
        list1.addAll(list2);
        int[] arr = new int[nums.length * 2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list1.get(i);
        }
        return arr;
    }

    // 剑指 Offer 53 - I. 在排序数组中查找数字 I
    public int search(int[] nums, int target) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                num++;
            }
        }
        return num;
    }

    // 剑指 Offer 42. 连续子数组的最大和
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(pre, max);
        }
        return max;
    }

    // 5161. 可以输入的最大单词数
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] wordArr = text.split(" ");
        int count = wordArr.length;
        char[] chars = brokenLetters.toCharArray();
        for (int i = 0; i < wordArr.length; i++) {
            for (int i1 = 0; i1 < chars.length; i1++) {
                if (wordArr[i].contains(String.valueOf(chars[i1]))) {
                    count--;
                    break;
                }
            }
        }
        return count;
    }

    // 5804. 检查是否所有字符出现次数相同
    public boolean areOccurrencesEqual(String s) {
        char[] chars = s.toCharArray();
        String first = String.valueOf(chars[0]);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            String charStr = String.valueOf(chars[i]);
            if (map.containsKey(charStr)) {
                map.put(charStr, (Integer) map.get(charStr) + 1);
            } else {
                map.put(charStr, 1);
            }
        }
        int count = (Integer) map.get(first);
        if (map.entrySet().stream().anyMatch(e -> e.getValue() != count)) {
            return false;
        } else {
            return true;
        }

    }

    // 1736. 替换隐藏数字得到的最晚时间
    public String maximumTime(String time) {
        time = time.replace(":", "");
        char[] chars = time.toCharArray();
        if (chars[0] == '?') {
            if (chars[1] == '?') {
                chars[0] = '2';
            } else if (Integer.valueOf(String.valueOf(chars[1])) >= 4) {
                chars[0] = '1';
            } else {
                chars[0] = '2';
            }
        }
        chars[1] = chars[1] == '?' && chars[0] == '?' ? '3' : chars[1];
        chars[1] = chars[1] == '?' && chars[0] == '0' ? '9' : chars[1];
        chars[1] = chars[1] == '?' && chars[0] == '1' ? '9' : chars[1];
        chars[1] = chars[1] == '?' && chars[0] == '2' ? '3' : chars[1];
        chars[2] = chars[2] == '?' ? '5' : chars[2];
        chars[3] = chars[3] == '?' ? '9' : chars[3];
        return String.valueOf(chars[0]) + String.valueOf(chars[1]) + ":" + String.valueOf(chars[2]) + String.valueOf(chars[3]);
    }

    // 171. Excel表列序号
    public int titleToNumber(String columnTitle) {
        char[] chars = columnTitle.toCharArray();
        int index = 0;
        int num = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            num += charToInt(chars[i]) * Math.pow(26, index);
            index++;
        }
        return num;
    }

    public int charToInt(char word) {
        int num = word;
        return num - 64;
    }

    // 1337. 矩阵中战斗力最弱的 K 行
    public int[] kWeakestRows(int[][] mat, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> indexList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        // 循环数组 取得每一行中的数字大小
        for (int i = 0; i < mat.length; i++) {
            int num = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    num++;
                } else {
                    break;
                }
            }
            list.add(i, num);
        }

        while (indexList.size() < k) {
            int minValue = Integer.MAX_VALUE;
            for (Integer integer : list) {
                minValue = Math.min(minValue, integer);
            }

            // 将list的当前最小值的索引记录下来
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == minValue) {
                    // 将此时最小值索引赋值为最大值 避免下次循环再次找到当前最小值
                    list.set(i, Integer.MAX_VALUE);
                    indexList.add(i);
                    break;
                }
            }
        }

        int[] arr = new int[k];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = indexList.get(i);
        }
        return arr;
    }

    // 5830. 三除数
    public boolean isThree(int n) {
        if (n == 1) {
            return false;
        }
        double sqrt = Math.sqrt(n);
        if (sqrt % 1 == 0) {
            int mid = (int) sqrt;
            for (int i = 2; i < mid; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // 5193. 删除字符使字符串变好
    public String makeFancyString(String s) {
        char[] chars = s.toCharArray();
        char previous = chars[0];
        int index = 1;
        // 这里使用stringbuilder拼接字符串才通过测试用例 string += 效率太低导致测试用例超时
        StringBuilder result = new StringBuilder(String.valueOf(s.charAt(0)));
        for (int i = 1; i < chars.length; i++) {
            if (previous != chars[i]) {
                index = 0;
            }
            if (index >= 3) {
                continue;
            }
            previous = chars[i];
            index++;
            if (index < 3) {
                result.append(String.valueOf(chars[i]));
            }
        }
        return new String(result);
    }

    // 1137. 第 N 个泰波那契数
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 3] + arr[i - 2] + arr[i - 1];
        }
        return arr[arr.length - 1];
    }

    // 5838. 检查字符串是否为数组前缀
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            if (s.equals(sb.toString())) {
                return true;
            }
        }
        return false;
    }

    // 551. 学生出勤记录 I
    public boolean checkRecord(String s) {
        char[] chars = s.toCharArray();
        int lateNum = 0;
        int absendNum = 0;
        for (char aChar : chars) {
            if (aChar == 'A') {
                absendNum++;
                if (absendNum >= 2) {
                    return false;
                }
            }
            if (aChar == 'L') {
                lateNum++;
                if (lateNum >= 3) {
                    return false;
                }
            } else {
                lateNum = 0;
            }
        }
        return true;
    }

    // 5834. 使用特殊打字机键入单词的最少时间
    public int minTimeToType(String word) {
        char[] chars = word.toCharArray();
        char thelastchar = 'a';
        int num = 0;
        for (char aChar : chars) {
            int step = aChar - thelastchar;
            if (step < 0) {
                step = 0 - step;
            }
            if (step > 13) {
                step = 26 - step;
            }
            num = num + step + 1;
            thelastchar = aChar;
        }
        return num;
    }

    // 5850. 找出数组的最大公约数
    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        int min = nums[0];
        int max = nums[nums.length - 1];
        for (int i = min; i > 0; i--) {
            if (max % i == 0 && min % i == 0) {
                return i;
            }
        }
        return 1;
    }

    // 1646. 获取生成数组中的最大值
    public int getMaximumGenerated(int n) {
        int[] arr = new int[n + 1];
        int max = 0;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        arr[0] = 0;
        arr[1] = 1;
        max = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                arr[i] = arr[i / 2];
            } else {
                arr[i] = arr[i / 2] + arr[i / 2 + 1];
            }
            max = Math.max(arr[i], max);
        }
        return max;
    }

    // 5854. 学生分数的最小差值
    public int minimumDifference(int[] nums, int k) {
        // 1,4,7,9 2
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length && k - 1 + i < nums.length; i++) {
            int num = nums[k - 1 + i] - nums[i];
            min = Math.min(num, min);
        }
        return min;
    }

    // 5846. 找到数组的中间位置
    public int findMiddleIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (sumleft(i, nums) == sumright(i, nums)) {
                return i;
            }
        }
        return -1;
    }

    public int sumleft(int index, int[] nums) {
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public int sumright(int index, int[] nums) {
        int sum = 0;
        for (int i = nums.length - 1; i > index; i--) {
            sum += nums[i];
        }
        return sum;
    }

    //    1995. 统计特殊四元组
    public int countQuadruplets(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] == nums[l]) {
                            num++;
                        }
                    }
                }
            }
        }
        return num;
    }

    // 5859. 差的绝对值为 K 的数对数目
    public int countKDifference(int[] nums, int k) {
        int num = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    num++;
                }
            }
        }
        return num;
    }

    // 1436. 旅行终点站
    public String destCity(List<List<String>> paths) {
        Set<String> citiesA = new HashSet<String>();
        for (List<String> path : paths) {
            citiesA.add(path.get(0));
        }
        for (List<String> path : paths) {
            if (!citiesA.contains(path.get(1))) {
                return path.get(1);
            }
        }
        return "";
    }

    // 5871. 将一维数组转变成二维数组
    public int[][] construct2DArray(int[] original, int m, int n) {
        int length = original.length;
        int total = m * n;
        if (total != length) {
            return new int[0][0];
        }
        int[][] arr = new int[m][n];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = original[index];
                index++;
            }
        }
        return arr;
    }

    // 5872. 连接后等于目标字符串的字符串对
    public int numOfPairs(String[] nums, String target) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && (nums[i] + nums[j]).equals(target)) {
                    total++;
                }
            }
        }
        return total;
    }

    // 482. 密钥格式化
    public String licenseKeyFormatting(String s, int k) {
        s = s.toUpperCase();
        s = s.replaceAll("-", "");
        int length = s.length();
        int firstStr = length % k;
        int n = length / k;
        String result = "";
        if (firstStr != 0) {
            result = s.substring(0, firstStr);
            if (n != 0) {
                result += "-";
            }
            s = s.substring(firstStr, length);
        }
        for (int i = 0; i < n; i++) {
            result += s.substring(i * k, (i + 1) * k);
            if (i != n - 1) {
                result += "-";
            }
        }
        return result;
    }


    // 434. 字符串中的单词数
    public int countSegments(String s) {
        int cnt = 0;
        String[] ans = s.split(" ");
        for (int i = 0; i < ans.length; i++) {
            String str = ans[i].trim();
            if (!"".equals(str)) {
                cnt++;
            }
        }
        return cnt;
    }

    // 剑指 Offer II 069. 山峰数组的顶部
    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i + 1] < arr[i]) {
                return i;
            }
        }
        return 1;
    }

    // 5885. 使每位学生都有座位的最少移动次数
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int num = 0;
        for (int i = 0; i < seats.length; i++) {
            num += Math.abs(seats[i] - students[i]);
        }
        return num;
    }

    // 5902. 检查句子中的数字是否递增
    public boolean areNumbersAscending(String s) {
        int num = Integer.MIN_VALUE;
        String[] s1 = s.split(" ");
        for (int i = 0; i < s1.length; i++) {
            boolean isNum = s1[i].matches("[0-9]+");
            if (isNum) {
                if (num < Integer.parseInt(s1[i])) {
                    num = Integer.parseInt(s1[i]);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    // 5898. 数组中第 K 个独一无二的字符串
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String s : arr) {
            if (map.containsKey(s)) {
                map.put(s, (Integer) map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        int n = 0;
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            if (stringIntegerEntry.getValue() == 1) {
                n++;
                if (n == k) {
                    return stringIntegerEntry.getKey();
                }
            }
        }
        return "";
    }

    // 5914. 值相等的最小索引
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    // 268. 丢失的数字
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    // 5918. 统计字符串中的元音子字符串
    public int countVowelSubstrings(String word) {
        if (word.length() < 5) {
            return 0;
        }
        int count = 0;
        int length = word.length();
        for (int i = 0; i + 5 <= length; i++) {
            int n = i + 5;
            String wordstr = word.substring(i, n);
            while (checkVowel(wordstr) == 0 || checkVowel(wordstr) == 1) {
                if (checkVowel(wordstr) == 0) {
                    count++;
                }
                n++;
                if (n > length) {
                    break;
                }
                wordstr = word.substring(i, n);
            }
        }
        return count;
    }

    public int checkVowel(String word) {
        // 判断是否满足连续且只有元音的字符串
        char[] chars = word.toCharArray();
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        for (char aChar : chars) {
            set.remove(aChar);
            if (aChar != 'a' && aChar != 'e' && aChar != 'i' && aChar != 'o' && aChar != 'u') {
                return 2;
            }
        }
        if (set.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }

    // 5910. 检查两个字符串是否几乎相等
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] arr = new int[26];
        char[] chars = word1.toCharArray();
        char[] chars1 = word2.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            arr[chars[i] - 'a']++;
            arr[chars1[i] - 'a']--;
        }
        for (int i : arr) {
            if (Math.abs(i) > 3) {
                return false;
            }
        }
        return true;
    }

    // 5926. 买票需要的时间
    public int timeRequiredToBuy(int[] tickets, int k) {
        int sum = 0;
        int n = tickets[k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < tickets.length; j++) {
                if (tickets[j] == 0) {
                    continue;
                }
                sum++;
                tickets[j]--;
                if (tickets[k] == 0) {
                    return sum;
                }
            }
        }
        return sum;
    }

    // 5922. 统计出现过一次的公共字符串
    public int countWords(String[] words1, String[] words2) {
        Map<String, Object> map = new HashMap<>();
        for (String s : words1) {
            if (map.containsKey(s)) {
                int n = (Integer) map.get(s);
                map.put(s, ++n);
            } else {
                map.put(s, 1);
            }
        }
        int count = 0;
        Map<String, Object> existMap = new HashMap<>();
        for (String s : words2) {
            if (existMap.containsKey(s) && map.containsKey(s)) {
                count--;
                map.remove(s);
            }
            if (map.containsKey(s) && 1 == (Integer) map.get(s)) {
                count++;
                existMap.put(s, 1);
            }
        }
        return count;
    }

    // 5938. 找出数组排序后的目标下标
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                list.add(i);
            }
        }
        return list;
    }

    // 1005. K 次取反后最大化的数组和
    public int largestSumAfterKNegations(int[] nums, int k) {
        int total = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (k > 0 && nums[i] < 0) {
                nums[i] = 0 - nums[i];
                k--;
            }
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (k % 2 != 0) {
                nums[i] = 0 - nums[i];
                k = 0;
            }
            total += nums[i];
        }
        return total;
    }

    // 383. 赎金信
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        char[] chars = magazine.toCharArray();
        for (char aChar : chars) {
            arr[aChar - 'a']++;
        }
        char[] chars1 = ransomNote.toCharArray();
        for (char c : chars1) {
            int n = arr[c - 'a'];
            if (n > 0) {
                arr[c - 'a']--;
            } else {
                return false;
            }
        }
        return true;
    }

    // 5942. 找出 3 位偶数
    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> set = new HashSet<>();
        // 百位
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == 0) {
                continue;
            }
            // 十位
            for (int i1 = 0; i1 < digits.length; i1++) {
                if (i1 == i) {
                    continue;
                }
                // 个位
                for (int i2 = 0; i2 < digits.length; i2++) {
                    if (i2 == i1 || i2 == i) {
                        continue;
                    }
                    if (digits[i2] % 2 != 0) {
                        continue;
                    }
                    int num = digits[i] * 100 + digits[i1] * 10 + digits[i2];
                    set.add(num);
                }
            }
        }
        Integer[] arr = set.toArray(new Integer[0]);
        Arrays.sort(arr);
        return Arrays.stream(arr).mapToInt(Integer::valueOf).toArray();
    }

    // 1816. 截断句子-简单
    public String truncateSentence(String s, int k) {
        String[] s1 = s.split(" ");
        String s2 = "";
        for (int i = 0; i < k; i++) {
            if (i == k - 1) {
                s2 += s1[i];
            } else {
                s2 += s1[i] + " ";
            }
        }
        return s2;
    }

    // 5934. 找到和最大的长度为 K 的子序列
    public int[] maxSubsequence(int[] nums, int k) {
        int[] ori = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);
        int size = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = size - 1, j = k - 1; j >= 0; i--, j--) {
            list.add(nums[i]);
        }
        int[] arr = new int[k];
        int j = 0;
        for (int num : ori) {
            for (int i = 0; i < list.size(); i++) {
                if (num == list.get(i)) {
                    arr[j] = num;
                    j++;
                    list.remove(i);
                    break;
                }
            }

        }
        return arr;
    }

    // 5946. 句子中的最多单词数
    public int mostWordsFound(String[] sentences) {
        int max = 0;
        for (String sentence : sentences) {
            max = Math.max(sentence.split(" ").length, max);
        }
        return max;
    }

    // 5947. 从给定原材料中找到所有可以做出的菜
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < recipes.length; i++) {
            List<String> strings = ingredients.get(i);
            Boolean getRecipesFlag = Boolean.valueOf(true);
            for (String ingredient : strings) {
                Boolean flag = Boolean.valueOf(false);
                for (String supply : supplies) {
                    if (ingredient.equals(supply)) {
                        flag = Boolean.valueOf(true);
                    }
                }
                if (!flag) {
                    getRecipesFlag = Boolean.valueOf(false);
                    break;
                }
            }
            if (getRecipesFlag) {
                list.add(recipes[i]);
            }
        }
        return list;
    }

    // 846. 一手顺子
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Arrays.sort(hand);
        if (hand.length % groupSize != 0) {
            return false;
        }
        if (groupSize == 1) {
            return true;
        }
        int n = groupSize;
        int pre = -1;
        List<Integer> list = Arrays.stream(hand).boxed().collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            if (pre == -1) {
                pre = list.get(i);
                list.set(i, -1);
                n--;
                continue;
            }

            if (pre == list.get(i)) {
                continue;
            } else if (list.get(i) - 1 != pre) {
                return false;
            }

            pre = list.get(i);

            list.set(i, -1);
            n--;
            if (n == 0) {
                n = groupSize;
                pre = -1;
                clearList(list);
                i = -1;
            }
        }
        if (list.size() > 0) {
            return false;
        } else {
            return true;
        }

        // 哈希
       /* if (hand == null || hand.length == 0 || hand.length % groupSize != 0) {
            return false;
        }
        // 数组进行排序预处理，并将元素及其出现次数存储在 map 中
        Arrays.sort(hand);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int h : hand) {
            if (map.get(h) > 0) {
                // 判断 map 中是否有足够的元素构成顺子
                for (int j = 0; j < groupSize; j++) {
                    if (map.getOrDefault(h + j, 0) > 0) {
                        map.put(h + j, map.get(h + j) - 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;*/
    }

    public void clearList(List<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) == -1) {
                list.remove(i);
            }
        }
    }

    // 507. 完美数
    public boolean checkPerfectNumber(int num) {
        int total = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                total += i;
            }
        }
        if (total == num) {
            return true;
        } else {
            return false;
        }
    }

    // 2022. 将一维数组转变成二维数组
    public int[][] construct2DArray1(int[] original, int m, int n) {
        if (original.length != m * n) {
            return new int[0][0];
        }

        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = original[i * n + j];
            }
        }
        return arr;
    }

    // 1185. 一周中的第几天 1970年12月31日 星期四（Thursday）
    // 闰年 能被4整除且不能被100整除 或者 能被400整除
    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int totalDay = 0;
        // 计算年相差的总天数
        for (int i = 1971; i < year; i++) {
            totalDay += (i % 4 == 0 && i % 100 != 0) || (i % 400 == 0) ? 366 : 365;
        }
        // 计算月的天数
        int[] monthDay = {1, 31, (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        for (int i = 1; i < month; i++) {
            totalDay += monthDay[i];
        }
        totalDay += day;
        return week[(totalDay + 4) % 7];
    }

    // 1576. 替换所有的问号
    public String modifyString(String s) {
        if ("?".equals(s)) {
            return "a";
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                if (i == 0) {
                    chars[i] = getCharword('1', chars[i + 1]);
                } else if (i == s.length() - 1) {
                    chars[i] = getCharword(chars[i - 1], '1');
                } else {
                    chars[i] = getCharword(chars[i - 1], chars[i + 1]);
                }
            }
        }
        return new String(chars);
    }

    private char getCharword(char pre, char next) {
        char midChar = '0';
        for (int i = 0; i < 26; i++) {
            if (pre == 'a' + i || next == 'a' + i) {
                continue;
            } else {
                midChar = (char) ('a' + i);
            }
        }
        return midChar;
    }

    // 1614. 括号的最大嵌套深度
    public int maxDepth1(String s) {
        char[] chars = s.toCharArray();
        int max = 0;
        int left = 0;
        for (char aChar : chars) {
            if (aChar == '(') {
                left++;
            }
            if (aChar == ')') {
                left--;
            }
            max = Math.max(max, left);
        }
        return max;
    }

    // 5960. 将标题首字母大写
    public String capitalizeTitle(String title) {
        String[] s = title.split(" ");
        String str = "";
        for (String s1 : s) {
            if (s1.length() >= 3) {
                str += s1.substring(0, 1).toUpperCase() + s1.substring(1, s1.length()).toLowerCase() + " ";
            } else {
                str += s1.toLowerCase() + " ";
            }
        }
        return str.trim();
    }

    // 5961. 链表最大孪生和
    public int pairSum(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int max = 0;
        for (int i = 0; i < list.size() / 2; i++) {
            max = Math.max((list.get(i) + list.get(list.size() - 1 - i)), max);
        }
        return max;
    }

    // 5962. 连接两字母单词得到的最长回文串 （超时）
    public int longestPalindrome(String[] words) {
        int max = 0;
        boolean sameFlag = true;
        for (int i = 0; i < words.length; i++) {
            if ("".equals(words[i])) {
                continue;
            }
            for (int j = i; j < words.length; j++) {
                if (i == j) {
                    continue;
                }
                if (words[i].equals(getPalindrome(words[j]))) {
                    max += 4;
                    words[i] = "";
                    words[j] = "";
                    break;
                }
            }

            if (!"".equals(words[i]) && sameWord(words[i]) && sameFlag) {
                max += 2;
                sameFlag = false;
            }
        }
        return max;
    }

    // 转换为回文字符串
    private String getPalindrome(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // 判断是否是重复的字符
    private Boolean sameWord(String str) {
        return str.charAt(0) == str.charAt(1) ? true : false;
    }

    // 1629. 按键持续时间最长的键
    public char slowestKey1(int[] releaseTimes, String keysPressed) {
        Map<Character, Integer> map = new HashMap<>();
        int maxTime = 0;
        char aa = '0';
        for (int i = 0; i < releaseTimes.length; i++) {
            if (i == 0) {
                map.put(keysPressed.charAt(i), map.getOrDefault(keysPressed.charAt(i), 0) + releaseTimes[i]);
            } else {
                map.put(keysPressed.charAt(i), Math.max(map.getOrDefault(keysPressed.charAt(i), 0), (releaseTimes[i] - releaseTimes[i - 1])));
            }
        }
        for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
            if (characterIntegerEntry.getValue() > maxTime) {
                aa = characterIntegerEntry.getKey();
            } else if (characterIntegerEntry.getValue() == maxTime && characterIntegerEntry.getKey() > aa) {
                aa = characterIntegerEntry.getKey();
            }
            maxTime = Math.max(maxTime, characterIntegerEntry.getValue());
        }
        return aa;
    }

    // 5976. 检查是否每一行每一列都包含全部整数
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> mapX = new HashMap<>();
            Map<Integer, Integer> mapY = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (mapX.containsKey(matrix[i][j]) || matrix[i][j] > n) {
                    return false;
                } else {
                    mapX.put(matrix[i][j], 0);
                }
                if (mapY.containsKey(matrix[j][i]) || matrix[j][i] > n) {
                    return false;
                } else {
                    mapY.put(matrix[j][i], 0);
                }
            }
        }
        return true;
    }


}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */