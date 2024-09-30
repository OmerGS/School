<?php

function quality_theme_data_setup() {
    return $theme_options = array(
        //Logo and Fevicon header			
        'home_feature' => QUALITY_TEMPLATE_DIR_URI . '/images/slider/slide.jpg',
        'upload_image_logo' => '',
        'height' => '80',
        'width' => '200',
        'text_title' => false,
        'upload_image_favicon' => '',
        'style_sheet' => 'default.css',
        /* Home Image */
        'slider_enable' => true,
        'home_image_title' => esc_html__('Lorem Ipsum', 'quality'),
        'home_image_sub_title' => esc_html__('Welcome to Quality theme', 'quality'),
        'home_image_description' => esc_html__('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin pharetra volutpat vehicula.', 'quality'),
        'home_image_button_text' => esc_html__('Etiam sit amet', 'quality'),
        'home_image_button_link' => 'https://webriti.com/quality-lite-version-details-page/',
        'service_title' => esc_html__('Nam suscipit libero', 'quality'),
        'service_description' => esc_html__('Lorem Ipsum which looks reason able. The generated Lorem Ipsum is therefore always free.', 'quality'),
        'service_enable' => true,
        'service_one_title' => esc_html__('Donec tristique', 'quality'),
        'service_two_title' => esc_html__('Donec tristique', 'quality'),
        'service_three_title' => esc_html__('Donec tristique', 'quality'),
        'service_four_title' => esc_html__('Donec tristique', 'quality'),
        'service_one_icon' => 'fa fa-shield',
        'service_two_icon' => 'fa fa-tablet',
        'service_three_icon' => 'fa fa-edit',
        'service_four_icon' => 'fa fa-star-half-o',
        'service_one_text' => esc_html__('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'quality'),
        'service_two_text' => esc_html__('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'quality'),
        'service_three_text' => esc_html__('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'quality'),
        'service_four_text' => esc_html__('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'quality'),
        //Projects Section Settings
        'home_projects_enabled' => true,
        'project_heading_one' => esc_html__('Proin tincidunt tincidunt', 'quality'),
        'project_tagline' => esc_html__('Scelerisque eu lectus.', 'quality'),
        'project_one_thumb' => QUALITY_TEMPLATE_DIR_URI . '/images/project_thumb.png',
        'project_one_title' => esc_html__('Cras tempus', 'quality'),
        'project_two_thumb' => QUALITY_TEMPLATE_DIR_URI . '/images/project_thumb1.png',
        'project_two_title' => esc_html__('Cras tempus', 'quality'),
        'project_three_thumb' => QUALITY_TEMPLATE_DIR_URI . '/images/project_thumb2.png',
        'project_three_title' => esc_html__('Cras tempus', 'quality'),
        'project_four_thumb' => QUALITY_TEMPLATE_DIR_URI . '/images/project_thumb3.png',
        'project_four_title' => esc_html__('Cras tempus', 'quality'),
        //BLOG
        'home_blog_enabled' => true,
        'blog_heading' => esc_html__('Proin Tincidunt', 'quality'),
        'home_blog_description' => __('Cras <b>Eros</b> Elit', 'quality'),
        'home_meta_section_settings' => false,
        //Custom css
        'webrit_custom_css' => '',
        //Footer Customization
        'footer_copyright_text' => '<p>' . __('Proudly powered by <a href="https://wordpress.org">WordPress</a> | Theme: <a href="https://webriti.com" rel="nofollow">Quality</a> by Webriti', 'quality') . '</p>',
    );
}