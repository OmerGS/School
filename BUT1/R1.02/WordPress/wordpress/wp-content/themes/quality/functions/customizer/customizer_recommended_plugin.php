<?php

/* Notifications in customizer */


require get_template_directory() . '/functions/customizer-notify/quality-customizer-notify.php';


$quality_config_customizer = array(
    'recommended_plugins' => array(
        'webriti-companion' => array(
            'recommended' => true,
            'description' => sprintf('Install and activate <strong>Webriti Companion</strong> plugin for taking full advantage of all the features this theme has to offer %s.', 'quality'),
        ),
    ),
    'recommended_actions' => array(),
    'recommended_actions_title' => esc_html__('Recommended Actions', 'quality'),
    'recommended_plugins_title' => esc_html__('Recommended Plugin', 'quality'),
    'install_button_label' => esc_html__('Install and Activate', 'quality'),
    'activate_button_label' => esc_html__('Activate', 'quality'),
    'deactivate_button_label' => esc_html__('Deactivate', 'quality'),
);
Quality_Customizer_Notify::init(apply_filters('quality_customizer_notify_array', $quality_config_customizer));