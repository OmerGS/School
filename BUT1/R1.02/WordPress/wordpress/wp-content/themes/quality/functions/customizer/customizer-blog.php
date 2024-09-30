<?php

function quality_blog_customizer($wp_customize) {
    //Blog Heading section 
    $wp_customize->add_section(
            'blog_setting',
            array(
                'title' => esc_html__('Recent Blog Settings', 'quality'),
                'panel' => 'quality_homepage_section_setting',
                'priority' => 6,
            )
    );

    //Show and hide Blog section
    $wp_customize->add_setting(
            'quality_pro_options[home_blog_enabled]'
            ,
            array(
                'default' => true,
                'capability' => 'edit_theme_options',
                'sanitize_callback' => 'quality_sanitize_checkbox',
                'type' => 'option',
            )
    );
    $wp_customize->add_control(
            'quality_pro_options[home_blog_enabled]',
            array(
                'label' => esc_html__('Enable home blog section', 'quality'),
                'section' => 'blog_setting',
                'type' => 'checkbox',
            )
    );

    // hide meta content
    $wp_customize->add_setting(
            'quality_pro_options[home_meta_section_settings]',
            array(
                'default' => false,
                'capability' => 'edit_theme_options',
                'sanitize_callback' => 'quality_sanitize_checkbox',
                'type' => 'option',
            )
    );
    $wp_customize->add_control(
            'quality_pro_options[home_meta_section_settings]',
            array(
                'label' => esc_html__('Hide post meta from blog section', 'quality'),
                'section' => 'blog_setting',
                'type' => 'checkbox',
            )
    );



    // Blog Heading
    $wp_customize->add_setting(
            'quality_pro_options[blog_heading]',
            array('capability' => 'edit_theme_options',
                'default' => esc_html__('Proin Tincidunt', 'quality'),
                'type' => 'option',
                'sanitize_callback' => 'quality_blog_sanitize_text',
    ));

    $wp_customize->add_control(
            'quality_pro_options[blog_heading]',
            array(
                'type' => 'text',
                'label' => esc_html__('Title', 'quality'),
                'section' => 'blog_setting',
            )
    );

    // add section to manage news description
    $wp_customize->add_setting(
            'quality_pro_options[home_blog_description]',
            array(
                'default' => __('Cras <b>Eros</b> Elit', 'quality'),
                'capability' => 'edit_theme_options',
                'type' => 'option',
                'sanitize_callback' => 'quality_blog_sanitize_text',
            )
    );
    $wp_customize->add_control('quality_pro_options[home_blog_description]', array(
        'label' => esc_html__('Description', 'quality'),
        'section' => 'blog_setting',
        'type' => 'text',));

}

    function quality_blog_sanitize_text($input) {
        return wp_kses_post(force_balance_tags($input));
    }

add_action('customize_register', 'quality_blog_customizer');

/**
 * Add selective refresh for Front page section section controls.
 */
function quality_register_home_blog_section_partials($wp_customize) {

    $wp_customize->selective_refresh->add_partial('quality_pro_options[blog_heading]', array(
        'selector' => '.news .section-header p',
        'settings' => 'quality_pro_options[blog_heading]',
    ));

    $wp_customize->selective_refresh->add_partial('quality_pro_options[home_blog_description]', array(
        'selector' => '.news .section-header h1',
        'settings' => 'quality_pro_options[home_blog_description]',
    ));
}

add_action('customize_register', 'quality_register_home_blog_section_partials');