<?php

class quality_customize_import_dummy_data {

    private static $instance;

    public static function init() {
        if (!isset(self::$instance) && !( self::$instance instanceof quality_customize_import_dummy_data )) {
            self::$instance = new quality_customize_import_dummy_data;
            self::$instance->quality_setup_actions();
        }
    }

    /**
     * Setup the class props based on the config array.
     */

    /**
     * Setup the actions used for this class.
     */
    public function quality_setup_actions() {

        // Register the section
        //add_action( 'customize_register', array( $this, 'quality_customize_register' ) );
        // Enqueue scripts
        add_action('customize_controls_enqueue_scripts', array($this, 'quality_import_customize_scripts'), 0);
    }

    public function quality_import_customize_scripts() {

        wp_enqueue_script('quality-import-customizer-js', get_template_directory_uri() . '/js/quality-import-customizer.js', array('customize-controls'));
    }

    /* public function quality_customize_register( $wp_customize ) {

      require_once get_template_directory() . '/functions/custom_control/class-dummy-import-control.php';

      $wp_customize->register_section_type( 'quality_dummy_import' );

      $wp_customize->add_section(
      new quality_dummy_import(
      $wp_customize,
      'quality_import_section',
      array(
      'priority' => 0,
      )
      )
      );

      } */
}

$quality_import_customizer = array(
    'import_data' => array(
        'recommended' => true,
    ),
);
quality_customize_import_dummy_data::init(apply_filters('quality_import_customizer', $quality_import_customizer));