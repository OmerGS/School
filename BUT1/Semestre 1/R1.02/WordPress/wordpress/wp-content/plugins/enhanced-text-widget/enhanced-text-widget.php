<?php
/*
Plugin Name: Enhanced Text Widget
Plugin URI: http://wordpress.org/plugins/enhanced-text-widget/
Description: An enhanced version of the default text widget where you may have Text, HTML, CSS, JavaScript, Flash, Shortcodes, and/or PHP as content with linkable widget title.
Version: 1.6.2
Author: Clever Widgets
Author URI: https://themecheck.info
Text Domain: enhanced-text-widget
Domain Path: /languages
License: MIT
*/

// Exit if accessed directly.
if ( !defined('ABSPATH') ) exit;
require_once 'analyst/main.php';

analyst_init(array(
	'client-id' => 'ozb8r4zvwq369anp',
	'client-secret' => '63fcba7fd64e28937637a50f84450df531eb2dae',
	'base-dir' => __FILE__
));


class EnhancedTextWidget extends WP_Widget {

    /**
     * Widget construction
     */
    function __construct() {
        $widget_ops = array('classname' => 'widget_text enhanced-text-widget', 'description' => __( 'Text, HTML, CSS, PHP, Flash, JavaScript, Shortcodes', 'enhanced-text-widget' ));
        $control_ops = array( 'width' => 450 );
        parent::__construct('EnhancedTextWidget', __( 'Enhanced Text', 'enhanced-text-widget' ), $widget_ops, $control_ops);
        load_plugin_textdomain( 'enhanced-text-widget', false, basename( dirname( __FILE__ ) ) . '/languages' );
    }

    /**
     * Setup the widget output
     */
    function widget( $args, $instance ) {

        if (!isset($args['widget_id'])) {
          $args['widget_id'] = null;
        }

        extract($args);

        $title = apply_filters('widget_title', empty($instance['title']) ? '' : $instance['title'], $instance);
        $titleUrl = empty($instance['titleUrl']) ? '' : $instance['titleUrl'];
        $cssClass = empty($instance['cssClass']) ? '' : $instance['cssClass'];
        $text = apply_filters('widget_enhanced_text', $instance['text'], $instance);
        $hideTitle = !empty($instance['hideTitle']) ? true : false;
        $hideEmpty = !empty($instance['hideEmpty']) ? true : false;
        $newWindow = !empty($instance['newWindow']) ? true : false;
        $filterText = !empty($instance['filter']) ? true : false;
        $bare = !empty($instance['bare']) ? true : false;

        if ( $cssClass ) {
            if( strpos($before_widget, 'class') === false ) {
                $before_widget = str_replace('>', 'class="'. $cssClass . '"', $before_widget);
            } else {
                $before_widget = str_replace('class="', 'class="'. $cssClass . ' ', $before_widget);
            }
        }

        // Parse the text through PHP
        ob_start();
				eval('?>' . $text);
        $text = ob_get_contents();
        ob_end_clean();

        // Run text through do_shortcode
        $text = do_shortcode($text);

        if (!empty($text) || !$hideEmpty) {
            echo $bare ? '' : $before_widget;

            if ($newWindow) $newWindow = "target='_blank'";

            if(!$hideTitle && $title) {
                if($titleUrl) $title = "<a href='$titleUrl' $newWindow>$title</a>";
                echo $bare ? $title : $before_title . $title . $after_title;
            }

            echo $bare ? '' : '<div class="textwidget widget-text">';

            // Echo the content
            echo $filterText ? wpautop($text) : $text;

            echo $bare ? '' : '</div>' . $after_widget;
        }
    }

    /**
     * Run on widget update
     */
    function update( $new_instance, $old_instance ) {

				$user = wp_get_current_user();
				$allowed_roles = array( 'editor', 'administrator', 'author' );
				if ( !array_intersect( $allowed_roles, $user->roles ) ) {
						return;
				}

        $instance = $old_instance;
        $instance['title'] = strip_tags($new_instance['title']);
        if ( current_user_can('unfiltered_html') )
            $instance['text'] =  $new_instance['text'];
        else
            $instance['text'] = wp_filter_post_kses($new_instance['text']);
        $instance['titleUrl'] = strip_tags($new_instance['titleUrl']);
        $instance['cssClass'] = strip_tags($new_instance['cssClass']);
        $instance['hideTitle'] = isset($new_instance['hideTitle']);
        $instance['hideEmpty'] = isset($new_instance['hideEmpty']);
        $instance['newWindow'] = isset($new_instance['newWindow']);
        $instance['filter'] = isset($new_instance['filter']);
        $instance['bare'] = isset($new_instance['bare']);

        return $instance;

    }

    /**
     * Setup the widget admin form
     */
    function form( $instance ) {

				$user = wp_get_current_user();
				$allowed_roles = array( 'editor', 'administrator', 'author' );
				if ( !array_intersect( $allowed_roles, $user->roles ) ) {
						return;
				}

        $instance = wp_parse_args( (array) $instance, array(
            'title' => '',
            'titleUrl' => '',
            'cssClass' => '',
            'text' => ''
        ));
        $title = $instance['title'];
        $titleUrl = $instance['titleUrl'];
        $cssClass = $instance['cssClass'];
        $text = format_to_edit($instance['text']);
        ?>
        <style>
            .monospace {
                font-family: Consolas, Lucida Console, monospace;
            }
            .etw-credits {
                font-size: 0.9em;
                background: #F7F7F7;
                border: 1px solid #EBEBEB;
                padding: 4px 6px;
            }
        </style>
        <p>
            <label for="<?php echo $this->get_field_id('title'); ?>"><?php _e( 'Title', 'enhanced-text-widget' ); ?>:</label>
            <input class="widefat" id="<?php echo $this->get_field_id('title'); ?>" name="<?php echo $this->get_field_name('title'); ?>" type="text" value="<?php echo $title; ?>" />
        </p>

        <p>
            <label for="<?php echo $this->get_field_id('titleUrl'); ?>"><?php _e( 'URL', 'enhanced-text-widget' ); ?>:</label>
            <input class="widefat" id="<?php echo $this->get_field_id('titleUrl'); ?>" name="<?php echo $this->get_field_name('titleUrl'); ?>" type="text" value="<?php echo $titleUrl; ?>" />
        </p>

        <p>
            <label for="<?php echo $this->get_field_id('cssClass'); ?>"><?php _e( 'CSS Classes', 'enhanced-text-widget' ); ?>:</label>
            <input class="widefat" id="<?php echo $this->get_field_id('cssClass'); ?>" name="<?php echo $this->get_field_name('cssClass'); ?>" type="text" value="<?php echo $cssClass; ?>" />
        </p>

        <p>
            <label for="<?php echo $this->get_field_id('text'); ?>"><?php _e( 'Content', 'enhanced-text-widget' ); ?>:</label>
            <textarea class="widefat monospace" rows="16" cols="20" id="<?php echo $this->get_field_id('text'); ?>" name="<?php echo $this->get_field_name('text'); ?>"><?php echo $text; ?></textarea>
        </p>

        <p>
            <input id="<?php echo $this->get_field_id('hideTitle'); ?>" name="<?php echo $this->get_field_name('hideTitle'); ?>" type="checkbox" <?php checked(isset($instance['hideTitle']) ? $instance['hideTitle'] : 0); ?> />&nbsp;<label for="<?php echo $this->get_field_id('hideTitle'); ?>"><?php _e( 'Do not display the title', 'enhanced-text-widget' ); ?></label>
        </p>

        <p>
            <input id="<?php echo $this->get_field_id('hideEmpty'); ?>" name="<?php echo $this->get_field_name('hideEmpty'); ?>" type="checkbox" <?php checked(isset($instance['hideEmpty']) ? $instance['hideEmpty'] : 0); ?> />&nbsp;<label for="<?php echo $this->get_field_id('hideEmpty'); ?>"><?php _e( 'Do not display empty widgets', 'enhanced-text-widget' ); ?></label>
        </p>

        <p>
            <input type="checkbox" id="<?php echo $this->get_field_id('newWindow'); ?>" name="<?php echo $this->get_field_name('newWindow'); ?>" <?php checked(isset($instance['newWindow']) ? $instance['newWindow'] : 0); ?> />
            <label for="<?php echo $this->get_field_id('newWindow'); ?>"><?php _e( 'Open the URL in a new window', 'enhanced-text-widget' ); ?></label>
        </p>

        <p>
            <input id="<?php echo $this->get_field_id('filter'); ?>" name="<?php echo $this->get_field_name('filter'); ?>" type="checkbox" <?php checked(isset($instance['filter']) ? $instance['filter'] : 0); ?> />&nbsp;<label for="<?php echo $this->get_field_id('filter'); ?>"><?php _e( 'Automatically add paragraphs to the content', 'enhanced-text-widget' ); ?></label>
        </p>

        <p>
            <input id="<?php echo $this->get_field_id('bare'); ?>" name="<?php echo $this->get_field_name('bare'); ?>" type="checkbox" <?php checked(isset($instance['bare']) ? $instance['bare'] : 0); ?> />&nbsp;<label for="<?php echo $this->get_field_id('bare'); ?>"><?php _e( 'Do not output before/after_widget/title', 'enhanced-text-widget' ); ?></label>
        </p>

			<div class="etw-carousel-ad-trigger">
				<div class="etw-new-label"><?php _e( 'New', 'enhanced-text-widget' ); ?></div>
				<span><?php _e( 'Please check out our other plugins too!', 'enhanced-text-widget' ); ?></span>
				<div class="etw-check-it-label"><?php _e( 'Check it out', 'enhanced-text-widget' ); ?></div>
			</div>

    <?php

    }
}

/**
 * Register the widget
 */
function enhanced_text_widget_init() {
  register_widget( 'EnhancedTextWidget' );
}
add_action( 'widgets_init', 'enhanced_text_widget_init' );

add_action('admin_init', function () {
	require_once 'banner/misc.php';
});

/* Admin notice to check Classic Widgets Plugin is not already installed and WordPress version 5.8 or higher */
add_action( 'admin_notices', 'etw_flush_admin_notice__warning' );

function etw_flush_admin_notice__warning() {
    global $wp_version;

    if ( version_compare( $wp_version, '5.8' ) >= 0 && !is_plugin_active( 'classic-widgets/classic-widgets.php' ) ) {

    $etw_hide_admin_notification = get_option( 'etw_hide_admin_notification' );
    if( 'yes' === $etw_hide_admin_notification ) {
      return;
    }
    ?>
    <div class="notice notice-warning etw-notice-wrapper is-dismissible">
    <p><?php
        echo sprintf(
            __( '%1$sEnhanced Text Widget:%2$s This plugin provides a classic widget type, and needs the %3$sClassic Widgets%4$s plugin to function properly on this WordPress version.', 'ultimate-posts-widget' ),
            '<b>',
            '</b>',
            '<a href="https://wordpress.org/plugins/classic-widgets/" target="_blank">',
            '</a>'
        );
    ?></p>
    </div>
    <?php
    }
}

add_action( 'admin_print_footer_scripts', 'etw_admin_footer_js' );
function etw_admin_footer_js() {
    ?>
    <script id='etw-notice-js'>
        (function ($) {
            $( document ).ready( function() {
                $( document ).on( 'click', '.etw-notice-wrapper.is-dismissible .notice-dismiss', function () {
                    $.ajax({
                        type: "post",
                        dataType: "json",
                        url: "<?php echo admin_url( 'admin-ajax.php' ); ?>",
                        data : { action: "etw_hide_admin_notification" },
                    });
                });
            });
        })( jQuery );
        </script>
    <?php
}

add_action( 'wp_ajax_nopriv_etw_hide_admin_notification', 'etw_hide_admin_notification_callback' );
add_action( 'wp_ajax_etw_hide_admin_notification', 'etw_hide_admin_notification_callback' );

function etw_hide_admin_notification_callback() {

    $option_name = 'etw_hide_admin_notification';
    $new_value = 'yes';

    if ( get_option( $option_name ) !== false ) {
        update_option( $option_name, $new_value );
    } else {
        $deprecated = null;
        $autoload = 'no';
        add_option( $option_name, $new_value, $deprecated, $autoload );
    }
    wp_send_json_success();
    die;
}

// Activation of tryOutPlugins module
add_action('plugins_loaded', function () {

  if (!(class_exists('\Inisev\Subs\Inisev_Try_Out_Plugins') || class_exists('Inisev\Subs\Inisev_Try_Out_Plugins') || class_exists('Inisev_Try_Out_Plugins'))) {
    require_once __DIR__ . '/modules/tryOutPlugins/tryOutPlugins.php';
    $try_out_plugins = new \Inisev\Subs\Inisev_Try_Out_Plugins(__FILE__, __DIR__, 'Enhanced Text Widget', 'plugins.php?s=Enhanced%20Text%20Widget&plugin_status=all');
  }

});

add_filter( 'plugin_action_links_' . plugin_basename(__FILE__), function ($links) {

	$tifm_action = array('<a href="#!" id="etw_tifm_disable">' . __('Disable Plugin Test Feature', 'enhanced-text-widget') . '</a>');
	if (get_option('_tifm_feature_enabled') === 'disabled') {
		$tifm_action = array('<a href="#!" id="etw_tifm_enable">' . __('Enable Plugin Test Feature', 'enhanced-text-widget') . '</a>');
	}

	return array_merge($links, $tifm_action);

});

add_action('admin_footer', function () {

	global $pagenow;
	if ($pagenow === 'plugins.php') {
		?>
		<script type="text/javascript">
			(function () {
				let nonceTIFM = "<?php echo wp_create_nonce('tifm_notice_nonce') ?>";
				jQuery('#etw_tifm_enable').on('click', (e) => {
					e.preventDefault();
					jQuery.post(ajaxurl, { action: 'tifm_save_decision', decision: 'true', nonce: nonceTIFM }).done(() => {
						window.location.reload();
					}).fail(() => {
						alert('There was an error and we could not update this option.');
					});
				});
				jQuery('#etw_tifm_disable').on('click', (e) => {
					e.preventDefault();
					jQuery.post(ajaxurl, { action: 'tifm_save_decision', decision: 'false', nonce: nonceTIFM }).done(() => {
						window.location.reload();
					}).fail(() => {
						alert('There was an error and we could not update this option.');
					});
				});
			})();
		</script>
		<?php
	}

});

// Actions of tryOutPlugins
if (!has_action('wp_ajax_tifm_save_decision')) {
	add_action('wp_ajax_tifm_save_decision', function () {

		// Nonce verification
		if (!isset($_POST['nonce']) || !wp_verify_nonce(sanitize_text_field($_POST['nonce']), 'tifm_notice_nonce')) {
			wp_send_json_error();
			return;
		}

		if (isset($_POST['decision'])) {

			if ($_POST['decision'] == 'true') {
				update_option('_tifm_feature_enabled', 'enabled');
				delete_option('_tifm_disable_feature_forever', true);
				wp_send_json_success();
				exit;
			} else if ($_POST['decision'] == 'false') {
				update_option('_tifm_feature_enabled', 'disabled');
				update_option('_tifm_disable_feature_forever', true);
				wp_send_json_success();
				exit;
			} else if ($_POST['decision'] == 'reset') {
				delete_option('_tifm_feature_enabled');
				delete_option('_tifm_hide_notice_forever');
				delete_option('_tifm_disable_feature_forever');
				wp_send_json_success();
				exit;
			}

			wp_send_json_error();
			exit;

		}

	});
}
