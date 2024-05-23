public class Main {
    public static void main(String[] args) {
        int[] nums = new int[] {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST(nums);

        System.out.println(root.val);
        System.out.println(root.left.val);
        System.out.println(root.right.val);
        System.out.println(root.left.left.val);
        System.out.println(root.right.left.val);

        int[] nums2 = new int[]{0, 1, 2, 3, 4, 5};
        TreeNode root2 = sortedArrayToBST(nums2);
        
        System.out.println(root2.val);
        System.out.println(root2.left.val);
        System.out.println(root2.right.val);
        System.out.println(root2.left.left.val);
        System.out.println(root2.right.left.val);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }

    public static TreeNode sortedArrayToBST(int[] nums, int start, int end) {
       if(start >= end) {
           return null;
       }

        int mid = (end + start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = sortedArrayToBST(nums, start, mid);
        root.right = sortedArrayToBST(nums, mid + 1, end);

        return root;
    }


//    public static TreeNode BSTHelper(int[] nums, int start, int end) {
//        if(start > end) {
//            return null;
//        }
//
//        TreeNode root = new TreeNode(nums[end--]);
//
//        if(end >= 0 && root.val > nums[end]) {
//            root.left = BSTHelper(nums, start, end);
//        } else {
//            root.right = BSTHelper(nums, start, end);
//        }
//
//        return root;
//    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}