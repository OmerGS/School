<!DOCTYPE html>
<html <?php language_attributes(); ?>>
    <head>
        <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        ``
        <![endif]-->
        <meta charset="<?php bloginfo('charset'); ?>" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="profile" href="http://gmpg.org/xfn/11" />
        <!-- Theme Css -->
        <?php
        $quality_current_options = wp_parse_args(get_option('quality_pro_options', array()), quality_theme_data_setup());
        wp_head();
        ?>
    </head>
    <body <?php body_class(); ?>>

        <?php wp_body_open(); ?>
        <a class="skip-link screen-reader-text" href="#section-block"><?php esc_html_e('Skip to content', 'quality'); ?></a> 
        <!--Header Logo & Menus-->
        <nav class="navbar navbar-custom" role="navigation">
            <div class="container-fluid padding-0">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                      <?php 
                if($quality_current_options['text_title'] ==true && get_theme_mod('header_text')==false)
                { 
                    if((get_option('blogname')!='') || (get_option('blogdescription')!=''))
                    {?>
                    <div class="site-title">
                        <?php
                        if(get_option('blogname')!='')
                        {?>
                            <h2><a href="<?php echo esc_url( home_url( '/' ) ); ?>" rel="home">
                                <?php bloginfo( 'name' ); ?>
                            </a></h2>
                        <?php
                        }
                        $quality_description = get_bloginfo( 'description', 'display' );
                        if(get_option('blogdescription')!='')
                        {
                            if ( $quality_description || is_customize_preview() )
                            {
                                ?>
                            <p class="site-description"><?php echo $quality_description; ?></p>
                            <?php
                            }
                        }
                    ?></div>
                <?php } 
                } 
                elseif($quality_current_options['text_title'] ==false && $quality_current_options['upload_image_logo']!='' && !(has_custom_logo()) )
                { ?> 
                    <a class="navbar-brand" href="<?php echo esc_url(home_url( '/' )); ?>">
                        <img src="<?php echo esc_url($quality_current_options['upload_image_logo']); ?>" style="height:<?php if($quality_current_options['height']!='') { echo absint($quality_current_options['height']); }  else { "80"; } ?>px; width:<?php if($quality_current_options['width']!='') { echo absint($quality_current_options['width']); }  else { "200"; } ?>px;" alt="<?php echo esc_attr(get_bloginfo( 'title' )); ?>" />
                    </a>
                    <?php
                }   
                if(has_custom_logo() )
                { ?>
                    <a class="navbar-brand" href="<?php echo esc_url(home_url( '/' )); ?>">
                        <?php
                        if ( has_custom_logo() ) 
                        {   
                            $quality_custom_logo_id = get_theme_mod( 'custom_logo' );
                            $quality_post_status=get_post_status ( $quality_custom_logo_id );
                            $quality_my_options = get_option('quality_pro_options');
                            $quality_my_options['upload_image_logo'] = '';
                            update_option('quality_pro_options', $quality_my_options);
                            $quality_image = wp_get_attachment_image_src( $quality_custom_logo_id , 'full' );
                            echo '<img src="'.esc_url($quality_image[0]).'" alt="'. esc_attr(get_bloginfo( 'title' )).'" />';
                        }?>
                    </a>
                <?php
                }
		 ?>
                        <div class="site-title site-branding-text">
                            <?php
                            if(get_option('blogname')!='')
                            {?>
                                <h2><a href="<?php echo esc_url( home_url( '/' ) ); ?>" rel="home">
                                    <?php bloginfo( 'name' ); ?>
                                </a></h2>
                                
                            <?php
                            }
                            $quality_description = get_bloginfo( 'description', 'display' );
                            if(get_option('blogdescription')!='')
                            {
                                if ( $quality_description || is_customize_preview() )
                                { ?>
                                    <p class="site-description"><?php echo $quality_description; ?></p>
                                <?php }
                            }?>
                        </div>
                    
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#custom-collapse">
                        <span class="sr-only"><?php echo esc_html__('Toggle navigation','quality'); ?></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="custom-collapse">
                    <?php
                    wp_nav_menu(array(
                        'theme_location' => 'primary',
                        'container' => 'nav-collapse collapse navbar-inverse-collapse',
                        'menu_class' => 'nav navbar-nav navbar-right',
                        'fallback_cb' => 'quality_fallback_page_menu',
                        'walker' => new quality_nav_walker()
                            )
                    );
                    ?>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div class="clearfix"></div>