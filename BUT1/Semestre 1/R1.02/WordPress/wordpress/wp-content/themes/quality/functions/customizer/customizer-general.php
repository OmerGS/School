<?php

function quality_general_customizer($wp_customize) {

    //Theme color
    class Quality_color_Customize_Control extends WP_Customize_Control {

        public $type = 'new_menu';

        function render_content() {
            echo '<h3>' . esc_html__('Theme Color', 'quality') . '</h3>';
            $name = '_customize-color-radio-' . $this->id;
            foreach ($this->choices as $key => $value) {
                ?>
                <label>
                    <input type="radio" value="<?php echo esc_attr($key); ?>" name="<?php echo esc_attr($name); ?>" data-customize-setting-link="<?php echo esc_attr($this->id); ?>" <?php
                    if ($this->value() == $key) {
                        echo 'checked="checked"';
                    }
                    ?>>
                    <img <?php
                    if ($this->value() == $key) {
                        echo 'class="color_scheem_active"';
                    }
                    ?> src="<?php echo esc_url(get_template_directory_uri() . '/images/bg-patterns/'. $value); ?>" alt="<?php echo esc_attr($value); ?>" />
                </label>

                <?php
            }
            ?>
            <script>
                jQuery(document).ready(function ($) {
                    $("#customize-control-quality_pro_options-webriti_stylesheet label img").click(function () {
                        $("#customize-control-quality_pro_options-webriti_stylesheet label img").removeClass("color_scheem_active");
                        $(this).addClass("color_scheem_active");
                    });
                });
            </script>
            <?php
        }

    }

    /* General Section */
    $wp_customize->add_panel('general_options', array(
        'priority' => 400,
        'capability' => 'edit_theme_options',
        'title' => esc_html__('General Setting', 'quality'),
    ));

$wp_customize->add_section('bredcrumb_section',
		array(
			'title'     =>  esc_html__('Breadcrumb','quality'),
			'panel'     =>  'general_options',
			'priority'  =>  1  
		)
	);
	//Breadcrumbs Type 
	$wp_customize->add_setting(
	'quality_breadcrumb_type',
	array(
	'default'           =>  'default',
	'capability'        =>  'edit_theme_options',
	'sanitize_callback' =>  'quality_sanitize_select',
	));
	$wp_customize->add_control('quality_breadcrumb_type', array(
	'label' => esc_html__('Breadcrumb type','quality'),
	'description' => esc_html__( 'If you use other than "default" one you will need to install and activate respective plugins','quality') . '<b> Breadcrumb NavXT, Yoast SEO </b>' . __('and','quality') . '<b> Rank Math SEO</b>',
	'section' => 'bredcrumb_section',
	'setting' => 'quality_breadcrumb_type',
	'type'    =>  'select',
	'priority' => 1,
	'choices' =>  array(
		'default' => __('Default', 'quality'),
		'yoast'  => 'Yoast SEO',
		'rankmath'  => 'Rank Math',
		'navxt'  => 'NavXT'
		)
	));

    $wp_customize->add_section('theme_color', array(
        'priority' => 400,
        'capability' => 'edit_theme_options',
        'title' => esc_html__('Theme Color', 'quality'),
        'panel' => 'general_options',
    ));


    $wp_customize->add_setting(
            'quality_pro_options[style_sheet]', array(
        'default' => 'default.css',
        'capability' => 'edit_theme_options',
        'type' => 'option',
        'sanitize_callback' => 'sanitize_text_field',
    ));

    $wp_customize->add_control(new Quality_color_Customize_Control($wp_customize, 'quality_pro_options[style_sheet]',
                    array(
                'label' => esc_html__('Theme Color Schemes', 'quality'),
                'section' => 'theme_color',
                'type' => 'radio',
                'choices' => array(
                    'default.css' => 'blue.png',
                    'red.css' => 'default.png',
                )
    )));
}

     //select sanitization function
		function quality_sanitize_select($input, $setting) {
			$input = sanitize_key($input);

			$choices = $setting->manager->get_control($setting->id)->choices;

			//return if valid
		return ( array_key_exists($input, $choices) ? $input : $setting->default );
		}

add_action('customize_register', 'quality_general_customizer');