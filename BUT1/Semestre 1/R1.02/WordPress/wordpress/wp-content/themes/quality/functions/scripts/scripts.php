<?php

function quality_scripts() {
    $quality_current_options = wp_parse_args(get_option('quality_pro_options', array()), quality_theme_data_setup());

    wp_enqueue_style('quality-bootstrap-css', QUALITY_TEMPLATE_DIR_URI . '/css/bootstrap.css');
    wp_enqueue_style('quality-style', get_stylesheet_uri());


    if (get_option('quality_pro_options') != '') {
        $quality_class = $quality_current_options['style_sheet'];
        wp_enqueue_style('default', QUALITY_TEMPLATE_DIR_URI . '/css/' . $quality_class);
    } else {
        wp_enqueue_style('quality-default', QUALITY_TEMPLATE_DIR_URI . '/css/default.css');
    }

    wp_enqueue_style('quality-theme-menu', QUALITY_TEMPLATE_DIR_URI . '/css/theme-menu.css');
    wp_enqueue_style('quality-font-awesome-min', QUALITY_TEMPLATE_DIR_URI . '/css/font-awesome/css/font-awesome.min.css');
    wp_enqueue_script('quality-bootstrap-js', QUALITY_TEMPLATE_DIR_URI . '/js/bootstrap.min.js', array('jquery'));
    wp_enqueue_script('quality-menu', QUALITY_TEMPLATE_DIR_URI . '/js/menu/menu.js', array('jquery'));
    wp_enqueue_style('quality-lightbox-css', QUALITY_TEMPLATE_DIR_URI . '/css/lightbox.css');
    wp_enqueue_script('quality-lightbox-js', QUALITY_TEMPLATE_DIR_URI . '/js/lightbox/lightbox.min.js');
}

add_action('wp_enqueue_scripts', 'quality_scripts');

if (is_singular()) {
    wp_enqueue_script("comment-reply");
}

function quality_custom_enqueue_css() {
    global $pagenow;
    if (in_array($pagenow, array('post.php', 'post-new.php', 'page-new.php', 'page.php'))) {
        wp_enqueue_style('quality-meta-box-css', QUALITY_TEMPLATE_DIR_URI . '/css/meta-box.css');
    }
}

add_action('admin_print_styles', 'quality_custom_enqueue_css', 10);

function quality_custmizer_style() {
    wp_enqueue_style('quality-customizer-css', QUALITY_TEMPLATE_DIR_URI . '/css/cust-style.css');
}

add_action('customize_controls_print_styles', 'quality_custmizer_style');

function quality_welcome_admin_css($hook) {

    if ('appearance_page_quality-welcome' != $hook) {
        return;
    }
    add_action('admin_head', 'quality_custom_admin_css');

    function quality_custom_admin_css() {
        echo '<style>
        body {
            background: #f1f1f1;
        }
            </style>';
    }

}

add_action('admin_enqueue_scripts', 'quality_welcome_admin_css');