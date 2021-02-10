/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : home.mytest.com:3306
 Source Schema         : cloud_goods

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 10/02/2021 11:40:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dn_attribute
-- ----------------------------
DROP TABLE IF EXISTS `dn_attribute`;
CREATE TABLE `dn_attribute`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '属性ID',
  `attr_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性类型：1单选 属性，2复选属性',
  `ordinal` int(10) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  `attr_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '属性名',
  `updated_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '修改时间',
  `created_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除：0否，1删除',
  `is_disabled` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否停用：0否 ，1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '属性表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dn_attribute_item
-- ----------------------------
DROP TABLE IF EXISTS `dn_attribute_item`;
CREATE TABLE `dn_attribute_item`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '属性子项ID',
  `attr_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性ID',
  `item_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '属性子项名称',
  `updated_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '修改时间',
  `created_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除：0否，1删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_attribute_id`(`attr_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '属性子项' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dn_brand
-- ----------------------------
DROP TABLE IF EXISTS `dn_brand`;
CREATE TABLE `dn_brand`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '品牌ID',
  `brand_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '品牌名称',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '品牌logo',
  `brand_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '品牌描述',
  `site_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '品牌官网地址',
  `ordinal` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序号 数字越大越靠前',
  `show_index` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否首页推荐 0为否 1为是',
  `created_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '添加时间',
  `updated_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `is_deleted` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '是否删除',
  `is_disabled` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '是否禁用，1：禁用，0：未禁用',
  `county_id` smallint(5) UNSIGNED NULL DEFAULT 0 COMMENT '国家ID',
  `group_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '产品系列ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_brand_name`(`brand_name`) USING BTREE,
  INDEX `idx_show_index`(`show_index`) USING BTREE,
  INDEX `idx_is_delete_statu`(`is_deleted`) USING BTREE,
  INDEX `idx_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品品牌' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dn_brand
-- ----------------------------
INSERT INTO `dn_brand` VALUES (65, '爱他美', '/202011/20201126112306-ev589uuX7i.png', NULL, '', 1, 1, 1606360988, 0, 0, 0, 0, 8);
INSERT INTO `dn_brand` VALUES (66, '英国牛栏', '/202011/20201126141256-K7rpwzsb9Z.png', NULL, '', 2, 1, 1606371180, 0, 0, 0, 0, 10);
INSERT INTO `dn_brand` VALUES (67, '爱他美奇迹系列', '/202011/20201126141844-K1MMP4n4Pb.jpeg', NULL, '', 3, 1, 1606371530, 0, 0, 0, 0, 16);
INSERT INTO `dn_brand` VALUES (68, '德国爱他美', '/202011/20201126141922-t2wPSpfFBe.png', NULL, '', 4, 1, 1606371565, 0, 0, 0, 0, 14);
INSERT INTO `dn_brand` VALUES (69, '英国爱他美', '/202011/20201126141955-lx67H9c7zx.png', NULL, '', 5, 1, 1606371597, 0, 0, 0, 0, 15);
INSERT INTO `dn_brand` VALUES (70, '测试品牌', '/202011/20201126142132-UCUSq7y7pc.png', NULL, '', 5, 1, 1606371695, 1607680151, 0, 1, 0, 18);
INSERT INTO `dn_brand` VALUES (71, '牛栏牌A2', '/202011/20201126142206-b3X13iKJ16.png', NULL, '', 6, 1, 1606371749, 0, 0, 0, 0, 13);
INSERT INTO `dn_brand` VALUES (72, '诺优能有机', '/202011/20201126142427-TRewdDEmMP.jpeg', NULL, '', 6, 1, 1606371869, 0, 0, 0, 0, 12);
INSERT INTO `dn_brand` VALUES (73, '澳洲_A2', '/202011/20201126162048-PnBUzNu47O.jpeg', NULL, '', 12, 1, 1606378850, 1606726835, 0, 0, 0, 9);
INSERT INTO `dn_brand` VALUES (74, '香港_A2', '/202011/20201126162108-m9YnNFpPPH.png', NULL, '', 11, 1, 1606378870, 0, 0, 0, 0, 9);
INSERT INTO `dn_brand` VALUES (75, '国产_A2', '/202011/20201126162135-ghRHlJZP9G.jpeg', NULL, '', 10, 1, 1606378899, 0, 0, 0, 0, 9);
INSERT INTO `dn_brand` VALUES (76, '澳洲爱他美', '/202011/20201128140631-FLOf3iwAmw.jpeg', NULL, '', 1, 1, 1606543611, 0, 0, 0, 0, 19);
INSERT INTO `dn_brand` VALUES (77, '测试品牌1', '/202012/20201211180334-GrFrlHMRkH.jpeg', NULL, '', 12, 1, 1607482541, 1608103328, 0, 0, 0, 10);

-- ----------------------------
-- Table structure for dn_brand_group
-- ----------------------------
DROP TABLE IF EXISTS `dn_brand_group`;
CREATE TABLE `dn_brand_group`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '产品系列名称',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图片',
  `ordinal` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序号 数字越大越靠前',
  `updated_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `created_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  `is_disabled` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态：0：启用，1：禁用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sort_order`(`ordinal`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品系列' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dn_brand_group
-- ----------------------------
INSERT INTO `dn_brand_group` VALUES (8, '爱他美', '/202011/20201126112249-NHGPDagDCp.png', 2, 0, 1606360972, 1, 0);
INSERT INTO `dn_brand_group` VALUES (9, 'A2_milk', '/202011/20201126133944-T9BTye1pnp.jpeg', 56, 1610509579, 1606369190, 1, 1);
INSERT INTO `dn_brand_group` VALUES (10, '英国牛栏', '/202011/20201126141206-djjG1j4GJl.png', 1, 1606725540, 1606371133, 0, 0);
INSERT INTO `dn_brand_group` VALUES (11, '可瑞康', '/202011/20201126141341-F2bXnLwdq2.png', 1, 1609742044, 1606371226, 0, 1);
INSERT INTO `dn_brand_group` VALUES (12, '诺优能有机', '/202011/20201126141415-H2huwpHH1H.jpeg', 20, 1606726645, 1606371258, 0, 0);
INSERT INTO `dn_brand_group` VALUES (13, '牛栏', '/202011/20201126141454-k332n1hyFE.png', 6, 1606391040, 1606371298, 0, 0);
INSERT INTO `dn_brand_group` VALUES (14, '德国爱他美', '/202011/20201126141538-KedjNEdqEE.png', 9, 0, 1606371342, 0, 0);
INSERT INTO `dn_brand_group` VALUES (15, '英国爱他美', '/202011/20201126141616-okFrff74n4.png', 55, 1606725913, 1606371389, 0, 1);
INSERT INTO `dn_brand_group` VALUES (16, '爱他美奇迹系列', '/202011/20201126141829-fkzzsCm9dD.png', 55, 1606726747, 1606371513, 0, 0);
INSERT INTO `dn_brand_group` VALUES (17, '荷兰牛栏', '/202011/20201126142308-P5yes3k6B5.jpeg', 55, 1606726280, 1606371790, 0, 0);
INSERT INTO `dn_brand_group` VALUES (18, '测试系列', '/202011/20201126162451-jjcHZwHsy5.jpeg', 5, 1606725523, 1606379093, 0, 0);
INSERT INTO `dn_brand_group` VALUES (19, '澳洲爱他美', '/202011/20201128140402-H2t1bDck8p.jpeg', 7, 1608103398, 1606543456, 0, 0);
INSERT INTO `dn_brand_group` VALUES (20, '测试1', '/test/ab1111c.img', 1, 1609748917, 1609747490, 1, 0);
INSERT INTO `dn_brand_group` VALUES (22, '测试', '/test/ab1111c.img', 1, 0, 1609748460, 0, 0);
INSERT INTO `dn_brand_group` VALUES (23, '测试2', '/test/ab1111c.img', 1, 0, 1609748930, 0, 0);
INSERT INTO `dn_brand_group` VALUES (24, '测试3', '/test/ab1111c.img', 1, 0, 1609749315, 0, 0);

-- ----------------------------
-- Table structure for dn_cart
-- ----------------------------
DROP TABLE IF EXISTS `dn_cart`;
CREATE TABLE `dn_cart`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `goods_sn` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品货号',
  `unit_torr` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '数量：单位（托）',
  `unit_con` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '数量：单位（箱）',
  `num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '数量：单位（罐）',
  `customer_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商户ID',
  `brand_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '品牌ID',
  `cat_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分类ID',
  `session_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '添加购物车时的session_id',
  `is_select` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否选择',
  `created_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id`, `goods_id`, `unit_torr`, `unit_con`, `num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 645 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '购物车' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dn_cart
-- ----------------------------
INSERT INTO `dn_cart` VALUES (245, 10, '', 1, 2, 278, 1, 71, 24, '', 0, 1606387592, 0);

-- ----------------------------
-- Table structure for dn_category
-- ----------------------------
DROP TABLE IF EXISTS `dn_category`;
CREATE TABLE `dn_category`  (
  `id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `cat_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `ioc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '分类图标',
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '关键词',
  `cat_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '描述',
  `parent_id` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父ID',
  `ordinal` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序号越大越靠前',
  `show_index` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否显示首页 0为否 1为是',
  `filter_attr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '筛选属性ID 用英文逗号分隔',
  `is_disabled` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态: 0启用1禁用',
  `leave` tinyint(3) NOT NULL DEFAULT 0 COMMENT '分类级别 1为顶级 2为2级 依次类推',
  `parentarr` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '到顶级的parent_id用逗号隔  如当前分类id为4   父级id为3   2,3,4',
  `created_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 0为否 1为是',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE,
  INDEX `idx_statu`(`is_disabled`, `is_deleted`) USING BTREE,
  INDEX `uniq_cat_name`(`cat_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dn_category
-- ----------------------------
INSERT INTO `dn_category` VALUES (1, '爱他美1', '', '', '', 0, 1, 1, '', 0, 0, '', 0, 0, 0);
INSERT INTO `dn_category` VALUES (2, '牛栏', '', '', '', 0, 2, 1, '', 0, 0, '', 1605269945, 0, 0);
INSERT INTO `dn_category` VALUES (3, '爱他美2', '', '', '', 0, 1, 1, '', 0, 0, '', 1605269997, 0, 0);
INSERT INTO `dn_category` VALUES (7, '爱他美3', '', '', '', 0, 211, 1, '', 0, 0, '', 1605270043, 1605490713, 0);
INSERT INTO `dn_category` VALUES (8, '品牌名称', '', '', '', 0, 1, 1, '', 0, 0, '', 1605370260, 0, 0);
INSERT INTO `dn_category` VALUES (9, '品牌名称', '', '', '', 0, 1, 1, '', 1, 0, '', 1605370263, 1606110292, 0);
INSERT INTO `dn_category` VALUES (22, '你好', '', '', '', 0, 2, 1, '', 1, 0, '', 1605606790, 1606699196, 0);
INSERT INTO `dn_category` VALUES (23, '羊奶粉', '', '', '', 0, 1, 1, '', 0, 0, '', 1606136056, 1606360958, 0);
INSERT INTO `dn_category` VALUES (24, '牛奶粉', '', '', '', 0, 11, 1, '', 0, 0, '', 1606186512, 1606360671, 0);
INSERT INTO `dn_category` VALUES (25, 'Milk_powder', '', '', '', 0, 2, 1, '', 0, 0, '', 1606371561, 1606373920, 0);
INSERT INTO `dn_category` VALUES (26, '测试分类', '', '', '', 0, 1, 1, '', 0, 0, '', 1606378994, 0, 0);
INSERT INTO `dn_category` VALUES (27, '可瑞康', '', '', '', 0, 6, 1, '', 0, 0, '', 1606543287, 1608103425, 0);

-- ----------------------------
-- Table structure for dn_goods
-- ----------------------------
DROP TABLE IF EXISTS `dn_goods`;
CREATE TABLE `dn_goods`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `goods_sn` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '货号',
  `goods_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主数据物料规格',
  `goods_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品类型 0单品 1组合商品',
  `cat_id` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分类ID',
  `brand_id` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '品牌ID',
  `gs_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '规格商品ID',
  `is_onsale` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '上架状态0下架1上架',
  `min_num` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '起购数',
  `max_num` smallint(5) UNSIGNED NOT NULL DEFAULT 1000 COMMENT '限购数 0为不限购',
  `base_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '成本价',
  `created_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '修改时间',
  `goods_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品主图',
  `goods_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品描述',
  `goods_attr_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '有商品规格 时 对应商品属性表ID',
  `validity_date` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '有效期',
  `shipping_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '模版ID',
  `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0正常1删除',
  `ordinal` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序号 数字越大越靠前',
  `sale_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销售数量（罐数） 提交订单就记数 取消订单 就减去对应的数量',
  `click_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览次数',
  `goods_number` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '第三方库存',
  `goods_frozen_number` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '第三方冻结库存',
  `goods_weight` decimal(10, 3) UNSIGNED NOT NULL DEFAULT 0.000 COMMENT '商品重量（KG）',
  `num_step` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '用户修改购买商品数量步长',
  `unit_torr_tin` smallint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '单位：1托多少听',
  `unit_con_tin` smallint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '单位：一箱多少听',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_shipping_id`(`shipping_id`) USING BTREE,
  INDEX `idx_cat_id`(`cat_id`) USING BTREE,
  INDEX `idx_brand_id`(`brand_id`) USING BTREE,
  INDEX `idx_is_delete`(`is_deleted`, `is_onsale`) USING BTREE,
  INDEX `idx_specs_id`(`goods_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售商品表  ' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dn_goods
-- ----------------------------
INSERT INTO `dn_goods` VALUES (7, '爱他美婴儿配方奶粉1段(0-6月龄）', '', '139466', 0, 3, 65, 0, 0, 0, 1000, 0.00, 1606370082, 1610349728, '/202011/20201126133915-Q3BwNWrRyr.png', '<p><img src=\"http://static.s.yuoucn.com/202011/20201126141957-MztyqeKHNq.jpeg\"/></p>', 0, '', 0, 0, 1, 1087, 0, 998, 0, 0.000, 1, 360, 6);
INSERT INTO `dn_goods` VALUES (8, '爱他美儿童配方奶粉4段（36月龄以上）', '', '139469', 0, 24, 65, 0, 1, 0, 1000, 0.00, 1606373870, 1606374162, '/202011/20201126145604-UW44ZNFQuA.jpeg', '<p><img src=\"http://static.s.yuoucn.com/202011/20201126145705-NGgtY005k0.png\"/><img src=\"http://static.s.yuoucn.com/202011/20201126145721-DFY00Zta34.png\"/><img src=\"http://static.s.yuoucn.com/202011/20201126145735-lKZ7i968M6.png\"/></p>', 0, '', 0, 0, 1, 1494, 0, 1000, 0, 0.000, 1, 200, 40);
INSERT INTO `dn_goods` VALUES (9, '爱他美幼儿配方奶粉3段（12-36月龄）', '', '139468', 0, 24, 67, 0, 1, 0, 1000, 0.00, 1606374174, 0, '/202011/20201126150230-FdZSLolY5g.png', '<p><img src=\"http://static.s.yuoucn.com/202011/20201126150043-MJt0RSD8SR.png\"/><img src=\"http://static.s.yuoucn.com/202011/20201126150057-XVSCWI8F7V.png\"/><img src=\"http://static.s.yuoucn.com/202011/20201126150109-g334LQqq46.png\"/></p>', 0, '', 0, 0, 1, 51, 0, 1000, 0, 0.000, 1, 100, 50);
INSERT INTO `dn_goods` VALUES (10, '香港牛栏牌A2奶粉4段 900g 3周岁及以上', '', '148530', 0, 24, 71, 0, 1, 0, 1000, 0.00, 1606374314, 0, '/202011/20201126150356-dxCE87ZB08.png', '<p><img src=\"http://static.s.yuoucn.com/202011/20201126150423-A24z6K4LKv.png\"/><img src=\"http://static.s.yuoucn.com/202011/20201126150434-Y55Wz3ZV4i.png\"/><img src=\"http://static.s.yuoucn.com/202011/20201126150457-dBdlpDs2ID.png\"/></p>', 0, '', 0, 0, 1, 168, 0, 1000, 0, 0.000, 1, 200, 39);
INSERT INTO `dn_goods` VALUES (11, '诺优能较大婴儿配方奶粉2段铁罐(6-12月龄）', '', '201458', 0, 24, 72, 0, 1, 0, 1000, 0.00, 1606374624, 0, '/202011/20201126150928-ZgN878m87i.jpeg', '<p><img src=\"http://static.s.yuoucn.com/202011/20201126151000-WG4u14XaSb.png\"/><img src=\"http://static.s.yuoucn.com/202011/20201126151016-TzYGEhy0H0.png\"/></p>', 0, '', 0, 0, 1, 192, 0, 1000, 0, 0.000, 1, 200, 50);
INSERT INTO `dn_goods` VALUES (12, '诺优能儿童配方奶粉4段铁罐(36-72月龄）', '', '201460', 0, 24, 72, 0, 1, 0, 1000, 0.00, 1606374760, 1606458754, '/202011/20201126151138-MDZbuZUgg3.jpeg', '<p><img src=\"http://static.s.yuoucn.com/202011/20201126151218-muaEHBeDLW.png\"/><img src=\"http://static.s.yuoucn.com/202011/20201126151233-PD9FlBhSF1.png\"/></p>', 0, '', 0, 0, 1, 1290, 0, 1000, 0, 0.000, 1, 200, 40);
INSERT INTO `dn_goods` VALUES (13, '香港 牛栏牌A2奶粉 2阶段 900g（6-12个月适用）', '', '139425', 0, 25, 74, 0, 1, 0, 1000, 0.00, 1606379933, 1608543162, '/202011/20201126195829-UfTTtl61Ta.jpeg', '<p>测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试<img src=\"http://static.s.yuoucn.com/202011/20201126164424-U577725oZ8.jpeg\"/></p>', 0, '', 0, 0, 1, 8615, 0, 1000, 0, 0.000, 1, 30, 15);
INSERT INTO `dn_goods` VALUES (14, '香港 牛栏牌A2奶粉 3阶段 900g（1-3岁适用）', '', '139426', 0, 25, 73, 0, 1, 0, 1000, 0.00, 1606380443, 1606897136, '/202011/20201126164713-YRvA9OvoPy.jpeg', '<p>测试</p>', 0, '', 0, 0, 1, 8360, 0, 1000, 0, 0.000, 1, 50, 10);
INSERT INTO `dn_goods` VALUES (15, '爱他美婴儿配方奶粉2段（6-12月龄）', '', '139467', 0, 24, 67, 0, 1, 0, 1000, 0.00, 1606543925, 1606899863, '/202011/20201128140834-ytYOUxBDgm.jpeg', '<p><img src=\"http://static.s.yuoucn.com/202011/20201128141141-sg3T5e5czt.jpeg\"/></p>', 0, '', 0, 0, 1, 740, 0, 1000, 0, 0.000, 1, 100, 100);
INSERT INTO `dn_goods` VALUES (16, '澳洲爱他美白金版婴儿配方奶粉4段 900g 3周岁及以上', '', '148528', 0, 2, 66, 0, 1, 0, 1000, 0.00, 1606877928, 1606898580, '/202012/20201202105739-apzIkVh5h7.jpeg', '<p><img src=\"http://static.s.yuoucn.com/202012/20201202105759-YMhfM32Hu4.png\"/></p>', 0, '', 0, 0, 1, 0, 0, 1000, 0, 0.000, 1, 50, 50);
INSERT INTO `dn_goods` VALUES (17, '爱他美Essensis奇迹白罐儿童配方奶粉 四段3周岁及以上 900g', '', '160106', 0, 7, 67, 0, 1, 0, 1000, 0.00, 1606896749, 1608542646, '/202012/20201202161202-Fu2s2oTUt2.jpeg', '<p><img src=\"http://static.s.yuoucn.com/202012/20201202161218-xOd7d73HbS.png\"/></p>', 0, '', 0, 0, 1, 0, 0, 1000, 0, 0.000, 1, 100, 50);
INSERT INTO `dn_goods` VALUES (18, '爱他美Essensis奇迹白罐较大婴儿配方奶粉 二段6-12月 900g', '', '160104', 0, 7, 73, 0, 1, 0, 1000, 0.00, 1606899396, 1607680360, '/202012/20201202165618-aX0Eae6ezZ.png', '<p>www</p>', 0, '', 0, 0, 1, 0, 0, 1000, 0, 0.000, 1, 23, 2);
INSERT INTO `dn_goods` VALUES (19, '爱他美Essensis奇迹白罐幼儿配方奶粉 三段1-3周岁 900g', '', '160105', 0, 7, 67, 0, 1, 0, 1000, 0.00, 1606899476, 1606899492, '/202012/20201202165727-COr033h77m.jpeg', '<p><img src=\"http://static.s.yuoucn.com/202012/20201202165809-pNSnYYC2iD.png\"/><img src=\"http://static.s.yuoucn.com/202012/20201202165752-iRklJ182kr.png\"/></p>', 0, '', 0, 0, 1, 0, 0, 1000, 0, 0.000, 1, 50, 20);
INSERT INTO `dn_goods` VALUES (20, '爱他美Essensis奇迹白罐婴儿配方奶粉 一段0-6月 900g', '', '160103', 0, 7, 73, 0, 1, 0, 1000, 0.00, 1606901552, 1606903584, '/202012/20201202173210-HEKww1K2D8.jpeg', '<p>测试测试测试</p>', 0, '', 0, 0, 1, 0, 0, 1000, 0, 0.000, 1, 100, 1);
INSERT INTO `dn_goods` VALUES (21, 'NUK 奶瓶刷子1个装', '', '104421', 0, 7, 73, 0, 1, 0, 1000, 0.00, 1606903296, 1606903302, '/202012/20201202180126-wx8xKK8AC1.jpeg', '<p>测试</p>', 0, '', 0, 0, 1, 0, 0, 1000, 0, 0.000, 1, 50, 10);
INSERT INTO `dn_goods` VALUES (22, '韩国 Pororo啵乐乐儿童双肩防走失书包 蓝色', '', '126321', 0, 26, 71, 0, 1, 0, 1000, 0.00, 1606903309, 0, '/202012/20201202180134-d8OSCOmuQU.png', '<p>水电费是zsdfvzsd</p>', 0, '', 0, 0, 1, 0, 0, 1000, 0, 0.000, 1, 20, 10);
INSERT INTO `dn_goods` VALUES (23, '法国 YSL圣罗兰 圆银管夹心糖果唇膏 ＃01 3.5g', '', '123046', 0, 24, 75, 0, 1, 0, 1000, 0.00, 1606903368, 1608025682, '/202012/20201202180239-cf4Uu5c1hC.jpeg', '<p>测试1搜嘎奥奥奥奥奥奥奥奥奥色若噶翁风阿尔泰过色若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若二人若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若若34WWWERAAAAAAA色鬼拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖颂德歌功过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过</p>', 0, '', 0, 0, 1, 0, 0, 1000, 0, 0.000, 1, 10, 2);
INSERT INTO `dn_goods` VALUES (24, '自游宝贝 婴儿充气泳池', '', '200347', 0, 24, 74, 0, 1, 0, 1000, 0.00, 1606903549, 1608103221, '/202012/20201202180539-FlhLscClBQ.jpeg', '<p>测试11<img src=\"http://static.s.yuoucn.com/202012/20201202180541-iFvLS2t4jk.jpeg\"/></p>', 0, '', 0, 0, 1, 0, 0, 1000, 0, 0.000, 1, 100, 100);
INSERT INTO `dn_goods` VALUES (26, '德国 雀巢贝巴BEBA112', '', '133014', 0, 26, 68, 0, 0, 0, 1000, 0.00, 1608107086, 1610350102, '/goods/202012/20210106100911-SUqYfUzDC6.png', '行动关系的关系电饭锅电饭锅的21', 0, '', 0, 0, 1, 0, 0, 1000, 0, 0.000, 1, 61, 2);
INSERT INTO `dn_goods` VALUES (27, '德国 雀巢贝巴BEBA11', '', '124521', 0, 26, 68, 0, 0, 0, 1000, 0.00, 1610337352, 0, '/goods/202012/20210106100911-SUqYfUzDC6.png', '行动关系的关系电饭锅电饭锅的', 0, '', 0, 0, 10, 0, 0, 0, 0, 0.000, 1, 61, 2);

-- ----------------------------
-- Table structure for dn_goods_album
-- ----------------------------
DROP TABLE IF EXISTS `dn_goods_album`;
CREATE TABLE `dn_goods_album`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `ordinal` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序号 数字越大越靠前',
  `img_type` tinyint(3) NOT NULL DEFAULT 0 COMMENT '图片类型，1：logo，2：详情相册',
  `img_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片名称',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片相对路径',
  `img_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片描述',
  `updated_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '修改时间',
  `created_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_goods_id`(`goods_id`, `is_deleted`, `ordinal`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品相册' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dn_goods_album
-- ----------------------------
INSERT INTO `dn_goods_album` VALUES (96, 0, 3, 2, '', '/goods/202012/20201216162434-f7gn7a4Gsi.png', '', 0, 1610337350, 0);
INSERT INTO `dn_goods_album` VALUES (97, 0, 2, 2, '', '/goods/202101/20210106100911-SUqYfUzDC6.png', '', 0, 1610337350, 0);
INSERT INTO `dn_goods_album` VALUES (98, 0, 1, 2, '', '/goods/202101/20210106100911-SUqYfUzDC6.png', '', 0, 1610337350, 0);
INSERT INTO `dn_goods_album` VALUES (111, 26, 1, 2, '', '/goods/202101/20210106100911-SUqYfUzDC6.png', '', 0, 1610350101, 0);

-- ----------------------------
-- Table structure for dn_goods_attribute
-- ----------------------------
DROP TABLE IF EXISTS `dn_goods_attribute`;
CREATE TABLE `dn_goods_attribute`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品ID',
  `cat_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分类ID',
  `attr_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性ID',
  `attr_item_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性子项ID',
  `created_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_goods_id`(`goods_id`) USING BTREE,
  INDEX `idx_category_id`(`cat_id`) USING BTREE,
  INDEX `idx_attribute_id`(`attr_id`) USING BTREE,
  INDEX `idx_attribute_item_id`(`attr_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dn_goods_price
-- ----------------------------
DROP TABLE IF EXISTS `dn_goods_price`;
CREATE TABLE `dn_goods_price`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '客户ID',
  `price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品客户价格' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dn_goods_price
-- ----------------------------
INSERT INTO `dn_goods_price` VALUES (26, 7, 100.00);
INSERT INTO `dn_goods_price` VALUES (27, 12, 200.00);
INSERT INTO `dn_goods_price` VALUES (28, 11, 111.00);
INSERT INTO `dn_goods_price` VALUES (29, 10, 122.00);
INSERT INTO `dn_goods_price` VALUES (30, 9, 200.00);
INSERT INTO `dn_goods_price` VALUES (31, 8, 120.00);
INSERT INTO `dn_goods_price` VALUES (32, 14, 10.00);
INSERT INTO `dn_goods_price` VALUES (33, 13, 20.00);
INSERT INTO `dn_goods_price` VALUES (34, 15, 20.00);
INSERT INTO `dn_goods_price` VALUES (35, 25, 49.00);
INSERT INTO `dn_goods_price` VALUES (36, 26, 99.00);

-- ----------------------------
-- Table structure for dn_import_price
-- ----------------------------
DROP TABLE IF EXISTS `dn_import_price`;
CREATE TABLE `dn_import_price`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `task_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务编号',
  `statu` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '记录状态 1：无效记录 2：待处理 3：已处理',
  `type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '记录类型 0：基础价格',
  `quantity` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '记录数量',
  `valid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '有效条数，不含重复的记录',
  `exec_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '执行条数',
  `executed_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '执行时间（系统在指定时间执行）',
  `created_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '管理员ID',
  `is_deleted` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '0正常1删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `task_no`(`task_no`) USING BTREE,
  INDEX `is_delete`(`is_deleted`) USING BTREE,
  INDEX `status`(`statu`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品价格导入' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dn_import_price
-- ----------------------------
INSERT INTO `dn_import_price` VALUES (142, '202012161626176070', 3, 0, 1, 1, 1, 1608107321, 1608107177, 1608107321, 0, 0);
INSERT INTO `dn_import_price` VALUES (143, '202012161629307325', 3, 0, 1, 1, 1, 1608107400, 1608107370, 1608107400, 0, 0);

-- ----------------------------
-- Table structure for dn_import_price_detail
-- ----------------------------
DROP TABLE IF EXISTS `dn_import_price_detail`;
CREATE TABLE `dn_import_price_detail`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `task_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '任务ID',
  `is_valid` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否有效 1：是 0：否',
  `goods_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品ID',
  `goods_sn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品货号',
  `goods_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主数据物料编码',
  `goods_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '记录类型 0：客户价格',
  `price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '导入的价格',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '提示文字',
  `is_execute` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否0为执行 1已执行',
  `created_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `executed_at` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '执行时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_task_id`(`task_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 492 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品价格导入明细' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dn_import_price_detail
-- ----------------------------
INSERT INTO `dn_import_price_detail` VALUES (491, 143, 1, 26, '', '133014', '德国 雀巢贝巴BEBA 至尊版SUPREME 婴幼儿奶粉PRE段 800g', 0, 99.00, '', 1, 1608107370, 1608107400, 1608107400);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_sn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `count` int(11) NULL DEFAULT 0 COMMENT '库存数量',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '单价',
  `num` int(10) UNSIGNED NULL DEFAULT 0,
  `updated_at` int(10) UNSIGNED NULL DEFAULT 0,
  `created_at` int(10) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_goods_code`(`goods_sn`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 'p001', '笔记本', 10, 3000.00, 974, 1609398474, 0);
INSERT INTO `goods` VALUES (2, 'p002', '手表', 5, 250.00, 1000, 0, 0);
INSERT INTO `goods` VALUES (3, 'p003', '键盘', 50, 100.00, 1000, 0, 0);
INSERT INTO `goods` VALUES (4, 'p004', '辣条', 1000, 0.50, 1000, 0, 0);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
