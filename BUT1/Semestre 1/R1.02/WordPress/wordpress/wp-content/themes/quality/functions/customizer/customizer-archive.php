<?php

function quality_archive_page_customizer($wp_customize) {

    $wp_customize->add_section(
            'breadcrumbs_setting',
            array(
                'title' => esc_html__('Archive Page Title', 'quality'),
                'description' => '',
                'priority' => 1050,
            )
    );

    $wp_customize->add_setting(
            'archive_prefix',
            array(
                'default' => esc_html__('Archive', 'quality'),
                'capability' => 'edit_theme_options',
                'sanitize_callback' => 'quality_template_page_sanitize_text',
            )
    );
    $wp_customize->add_control('archive_prefix', array(
        'label' => esc_html__('Archive', 'quality'),
        'section' => 'breadcrumbs_setting',
        'type' => 'text'
    ));

    $wp_customize->add_setting(
            'category_prefix',
            array(
                'default' => esc_html__('Category', 'quality'),
                'capability' => 'edit_theme_options',
                'sanitize_callback' => 'quality_template_page_sanitize_text',
            )
    );
    $wp_customize->add_control('category_prefix', array(
        'label' => esc_html__('Category', 'quality'),
        'section' => 'breadcrumbs_setting',
        'type' => 'text'
    ));

    $wp_customize->add_setting(
            'author_prefix',
            array(
                'default' => esc_html__('All posts by', 'quality'),
                'capability' => 'edit_theme_options',
                'sanitize_callback' => 'quality_template_page_sanitize_text',
            )
    );
    $wp_customize->add_control('author_prefix', array(
        'label' => esc_html__('Author', 'quality'),
        'section' => 'breadcrumbs_setting',
        'type' => 'text'
    ));

    $wp_customize->add_setting(
            'tag_prefix',
            array(
                'default' => esc_html__('Tag', 'quality'),
                'capability' => 'edit_theme_options',
                'sanitize_callback' => 'quality_template_page_sanitize_text',
            )
    );
    $wp_customize->add_control('tag_prefix', array(
        'label' => esc_html__('Tag', 'quality'),
        'section' => 'breadcrumbs_setting',
        'type' => 'text'
    ));


    $wp_customize->add_setting(
            'search_prefix',
            array(
                'default' => esc_html__('Search results for', 'quality'),
                'capability' => 'edit_theme_options',
                'sanitize_callback' => 'quality_template_page_sanitize_text',
            )
    );
    $wp_customize->add_control('search_prefix', array(
        'label' => esc_html__('Search', 'quality'),
        'section' => 'breadcrumbs_setting',
        'type' => 'text'
    ));

    $wp_customize->add_setting(
            '404_prefix',
            array(
                'default' => esc_html__('404', 'quality'),
                'capability' => 'edit_theme_options',
                'sanitize_callback' => 'quality_template_page_sanitize_text',
            )
    );
    $wp_customize->add_control('404_prefix', array(
        'label' => esc_html__('404', 'quality'),
        'section' => 'breadcrumbs_setting',
        'type' => 'text'
    ));


    $wp_customize->add_setting(
            'shop_prefix',
            array(
                'default' => esc_html__('Shop', 'quality'),
                'capability' => 'edit_theme_options',
                'sanitize_callback' => 'quality_template_page_sanitize_text',
            )
    );
    $wp_customize->add_control('shop_prefix', array(
        'label' => esc_html__('Shop', 'quality'),
        'section' => 'breadcrumbs_setting',
        'type' => 'text'
    ));
}

add_action('customize_register', 'quality_archive_page_customizer');

function quality_template_page_sanitize_text($input) {

    return wp_kses_post(force_balance_tags($input));
}
