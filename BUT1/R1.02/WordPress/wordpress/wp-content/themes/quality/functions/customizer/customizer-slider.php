<?php

function quality_slider_customizer($wp_customize) {
    $selective_refresh = isset($wp_customize->selective_refresh) ? true : false;

    //slider Section 
    $wp_customize->add_panel('quality_homepage_section_setting', array(
        'priority' => 500,
        'capability' => 'edit_theme_options',
        'title' => esc_html__('Homepage Section Settings', 'quality'),
    ));

    $wp_customize->add_section(
            'slider_section_settings',
            array(
                'title' => esc_html__('Slider Settings', 'quality'),
                'panel' => 'quality_homepage_section_setting',
                'priority' => 1,)
    );

    //Hide Index Slider Section

    $wp_customize->add_setting(
            'quality_pro_options[slider_enable]',
            array(
                'default' => true,
                'capability' => 'edit_theme_options',
                'sanitize_callback' => 'quality_sanitize_checkbox',
                'type' => 'option',
            )
    );

    $wp_customize->add_control(
            'quality_pro_options[slider_enable]',
            array(
                'label' => esc_html__('Enable slider on homepage.', 'quality'),
                'section' => 'slider_section_settings',
                'type' => 'checkbox',
            )
    );


    $wp_customize->add_setting('quality_pro_options[home_feature]', array('default' => get_template_directory_uri() . '/images/slider/slide.jpg',
        'type' => 'option', 'sanitize_callback' => 'esc_url_raw',
    ));

    $wp_customize->add_control(
            new WP_Customize_Image_Control(
                    $wp_customize,
                    'quality_pro_options[home_feature]',
                    array(
                'type' => 'upload',
                'label' => esc_html__('Image', 'quality'),
                'section' => 'example_section_one',
                'settings' => 'quality_pro_options[home_feature]',
                'section' => 'slider_section_settings',
                    )
            )
    );

    //Slider Title
    $wp_customize->add_setting(
            'quality_pro_options[home_image_title]', array(
        'default' => esc_html__('Lorem Ipsum', 'quality'),
        'capability' => 'edit_theme_options',
        'sanitize_callback' => 'sanitize_text_field',
        'type' => 'option',
    ));
    $wp_customize->add_control('quality_pro_options[home_image_title]', array(
        'label' => esc_html__('Title', 'quality'),
        'section' => 'slider_section_settings',
        'priority' => 150,
        'type' => 'text',
    ));

    //Slider sub title
    $wp_customize->add_setting(
            'quality_pro_options[home_image_sub_title]', array(
        'default' => esc_html__('Welcome to Quality theme', 'quality'),
        'capability' => 'edit_theme_options',
        'sanitize_callback' => 'sanitize_text_field',
        'type' => 'option',
    ));
    $wp_customize->add_control('quality_pro_options[home_image_sub_title]', array(
        'label' => esc_html__('Subtitle', 'quality'),
        'section' => 'slider_section_settings',
        'priority' => 150,
        'type' => 'text',
    ));

    //Slider Banner discription
    $wp_customize->add_setting(
            'quality_pro_options[home_image_description]', array(
        'default' => esc_html__('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin pharetra volutpat vehicula.', 'quality'),
        'capability' => 'edit_theme_options',
        'sanitize_callback' => 'sanitize_text_field',
        'type' => 'option',
    ));
    $wp_customize->add_control('quality_pro_options[home_image_description]', array(
        'label' => esc_html__('Description', 'quality'),
        'section' => 'slider_section_settings',
        'priority' => 150,
        'type' => 'text',
    ));


    // Slider banner button text
    $wp_customize->add_setting(
            'quality_pro_options[home_image_button_text]', array(
        'default' => esc_html__('Etiam sit amet', 'quality'),
        'capability' => 'edit_theme_options',
        'sanitize_callback' => 'sanitize_text_field',
        'type' => 'option',
    ));

    $wp_customize->add_control('quality_pro_options[home_image_button_text]', array(
        'label' => esc_html__('Button Text', 'quality'),
        'section' => 'slider_section_settings',
        'priority' => 150,
        'type' => 'text',
    ));

    // Slider banner button link
    $wp_customize->add_setting(
            'quality_pro_options[home_image_button_link]', array(
        'default' => 'https://webriti.com/quality-lite-version-details-page/',
        'capability' => 'edit_theme_options',
        'sanitize_callback' => 'esc_url_raw',
        'type' => 'option',
    ));

    $wp_customize->add_control('quality_pro_options[home_image_button_link]', array(
        'label' => esc_html__('Button Link', 'quality'),
        'section' => 'slider_section_settings',
        'priority' => 150,
        'type' => 'text',
    ));
}

add_action('customize_register', 'quality_slider_customizer');

/**
 * Add selective refresh for Front page section section controls.
 */
function quality_register_home_section_partials($wp_customize) {

    $wp_customize->selective_refresh->add_partial('quality_pro_options[home_feature]', array(
        'selector' => '#slider-carousel .item',
        'settings' => 'quality_pro_options[home_feature]',
    ));

    $wp_customize->selective_refresh->add_partial('quality_pro_options[home_image_title]', array(
        'selector' => '.slider-caption h5',
        'settings' => 'quality_pro_options[home_image_title]',
    ));

    $wp_customize->selective_refresh->add_partial('quality_pro_options[home_image_sub_title]', array(
        'selector' => '.slider-caption h1',
        'settings' => 'quality_pro_options[home_image_sub_title]',
    ));

    $wp_customize->selective_refresh->add_partial('quality_pro_options[home_image_description]', array(
        'selector' => '.slider-caption p',
        'settings' => 'quality_pro_options[home_image_description]',
    ));

    $wp_customize->selective_refresh->add_partial('quality_pro_options[home_image_button_text]', array(
        'selector' => '.slide-btn-area-sm a',
        'settings' => 'quality_pro_options[home_image_button_text]',
    ));
}

add_action('customize_register', 'quality_register_home_section_partials');