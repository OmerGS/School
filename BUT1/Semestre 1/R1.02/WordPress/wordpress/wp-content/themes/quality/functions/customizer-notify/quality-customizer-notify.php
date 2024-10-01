<?php

class Quality_Customizer_Notify {

	private $recommended_actions;

	
	private $recommended_plugins;

	
	private static $instance;

	
	private $recommended_actions_title;

	
	private $recommended_plugins_title;

	
	private $dismiss_button;

	
	private $install_button_label;

	
	private $activate_button_label;

	
	private $deactivate_button_label;

	
	public static function init( $config ) {
		if ( ! isset( self::$instance ) && ! ( self::$instance instanceof Quality_Customizer_Notify ) ) {
			self::$instance = new Quality_Customizer_Notify;
			if ( ! empty( $config ) && is_array( $config ) ) {
				self::$instance->config = $config;
				self::$instance->setup_config();
				self::$instance->setup_actions();
			}
		}

	}

	
	public function setup_config() {

		global $quality_customizer_notify_recommended_plugins;
		global $quality_customizer_notify_recommended_actions;

		global $install_button_label;
		global $activate_button_label;
		global $deactivate_button_label;

		$this->recommended_actions = isset( $this->config['recommended_actions'] ) ? $this->config['recommended_actions'] : array();
		$this->recommended_plugins = isset( $this->config['recommended_plugins'] ) ? $this->config['recommended_plugins'] : array();

		$this->recommended_actions_title = isset( $this->config['recommended_actions_title'] ) ? $this->config['recommended_actions_title'] : '';
		$this->recommended_plugins_title = isset( $this->config['recommended_plugins_title'] ) ? $this->config['recommended_plugins_title'] : '';
		$this->dismiss_button            = isset( $this->config['dismiss_button'] ) ? $this->config['dismiss_button'] : '';

		$quality_customizer_notify_recommended_plugins = array();
		$quality_customizer_notify_recommended_actions = array();

		if ( isset( $this->recommended_plugins ) ) {
			$quality_customizer_notify_recommended_plugins = $this->recommended_plugins;
		}

		if ( isset( $this->recommended_actions ) ) {
			$quality_customizer_notify_recommended_actions = $this->recommended_actions;
		}

		$install_button_label    = isset( $this->config['install_button_label'] ) ? $this->config['install_button_label'] : '';
		$activate_button_label   = isset( $this->config['activate_button_label'] ) ? $this->config['activate_button_label'] : '';
		$deactivate_button_label = isset( $this->config['deactivate_button_label'] ) ? $this->config['deactivate_button_label'] : '';

	}

	
	public function setup_actions() {

		// Register the section
		add_action( 'customize_register', array( $this, 'quality_plugin_notification_customize_register' ) );

		// Enqueue scripts and styles
		add_action( 'customize_controls_enqueue_scripts', array( $this, 'quality_customizer_notify_scripts_for_customizer' ), 0 );

		/* ajax callback for dismissable recommended actions */
		add_action( 'wp_ajax_quality_customizer_notify_dismiss_action', array( $this, 'quality_customizer_notify_dismiss_recommended_action_callback' ) );

		add_action( 'wp_ajax_ti_customizer_notify_dismiss_recommended_plugins', array( $this, 'quality_customizer_notify_dismiss_recommended_plugins_callback' ) );

	}

	
	public function quality_customizer_notify_scripts_for_customizer() {

		wp_enqueue_style( 'quality-customizer-notify-css', get_template_directory_uri() . '/functions/customizer-notify/css/quality-customizer-notify.css', array());

		wp_enqueue_style( 'plugin-install' );
		wp_enqueue_script( 'plugin-install' );
		wp_add_inline_script( 'plugin-install', 'var pagenow = "customizer";' );

		wp_enqueue_script( 'updates' );

		wp_enqueue_script( 'quality-customizer-notify-js', get_template_directory_uri() . '/functions/customizer-notify/js/quality-customizer-notify.js', array( 'customize-controls' ));
		wp_localize_script(
			'quality-customizer-notify-js', 'qualityCustomizercompanionObject', array(
				'ajaxurl'            => esc_url(admin_url( 'admin-ajax.php' )),
				'template_directory' => get_template_directory_uri(),
				'base_path'          => esc_url(admin_url()),
				'activating_string'  => esc_html__( 'Activating', 'quality' ),
			)
		);

	}

	
	public function quality_plugin_notification_customize_register( $wp_customize ) {

		
		require_once get_template_directory() . '/functions/customizer-notify/quality-customizer-notify-section.php';

		$wp_customize->register_section_type( 'Quality_Customizer_Notify_Section' );

		$wp_customize->add_section(
			new Quality_Customizer_Notify_Section(
				$wp_customize,
				'quality-customizer-notify-section',
				array(
					'title'          => $this->recommended_actions_title,
					'plugin_text'    => $this->recommended_plugins_title,
					'dismiss_button' => $this->dismiss_button,
					'priority'       => 0,
				)
			)
		);

	}

	
	public function quality_customizer_notify_dismiss_recommended_action_callback() {

		global $quality_customizer_notify_recommended_actions;

		$action_id = ( isset( $_GET['id'] ) ) ? $_GET['id'] : 0;

		echo esc_html( $action_id );

		if ( ! empty( $action_id ) ) {

			
			if ( get_option( 'quality_customizer_notify_show' ) ) {

				$quality_customizer_notify_show_recommended_actions = get_option( 'quality_customizer_notify_show' );
				switch ( $_GET['todo'] ) {
					case 'add':
						$quality_customizer_notify_show_recommended_actions[ $action_id ] = true;
						break;
					case 'dismiss':
						$quality_customizer_notify_show_recommended_actions[ $action_id ] = false;
						break;
				}
				update_option( 'quality_customizer_notify_show', $quality_customizer_notify_show_recommended_actions );

				
			} else {
				$quality_customizer_notify_show_recommended_actions_new = array();
				if ( ! empty( $quality_customizer_notify_recommended_actions ) ) {
					foreach ( $quality_customizer_notify_recommended_actions as $quality_lite_customizer_notify_recommended_action ) {
						if ( $quality_lite_customizer_notify_recommended_action['id'] == $action_id ) {
							$quality_customizer_notify_show_recommended_actions_new[ $quality_lite_customizer_notify_recommended_action['id'] ] = false;
						} else {
							$quality_customizer_notify_show_recommended_actions_new[ $quality_lite_customizer_notify_recommended_action['id'] ] = true;
						}
					}
					update_option( 'quality_customizer_notify_show', $quality_customizer_notify_show_recommended_actions_new );
				}
			}
		}
		die(); 
	}

	
	public function quality_customizer_notify_dismiss_recommended_plugins_callback() {

		$action_id = ( isset( $_GET['id'] ) ) ? $_GET['id'] : 0;

		echo esc_html( $action_id ); 

		if ( ! empty( $action_id ) ) {

			$quality_lite_customizer_notify_show_recommended_plugins = get_option( 'quality_customizer_notify_show_recommended_plugins' );

			switch ( $_GET['todo'] ) {
				case 'add':
					$quality_lite_customizer_notify_show_recommended_plugins[ $action_id ] = false;
					break;
				case 'dismiss':
					$quality_lite_customizer_notify_show_recommended_plugins[ $action_id ] = true;
					break;
			}
			update_option( 'quality_customizer_notify_show_recommended_plugins', $quality_lite_customizer_notify_show_recommended_plugins );
		}
		die(); 
	}

}
