<?php

/*--------------------------------------------------------------------*/
/*     Register Google Fonts
/*--------------------------------------------------------------------*/
function quality_fonts_url() {
	
    $quality_fonts_url = '';
		
    $quality_font_families = array();
 
	$quality_font_families = array('Open Sans:300,400,600,700,800','Roboto:100,300,400,500,600,700,900','Raleway:600','italic');
 
        $quality_query_args = array(
            'family' => urlencode( implode( '|', $quality_font_families ) ),
            'subset' => urlencode( 'latin,latin-ext' ),
        );
 
        $quality_fonts_url = add_query_arg( $quality_query_args, '//fonts.googleapis.com/css' );

    return esc_url($quality_fonts_url);
}
function quality_scripts_styles() {
    wp_enqueue_style( 'quality-fonts', quality_fonts_url(), array(), null );
}
add_action( 'wp_enqueue_scripts', 'quality_scripts_styles' );