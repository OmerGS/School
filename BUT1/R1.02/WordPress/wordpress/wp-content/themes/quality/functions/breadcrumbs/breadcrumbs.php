<?php
// theme sub header breadcrumb functions
function quality_curPageURL() {
    $pageURL = 'http';
    if (key_exists("HTTPS", $_SERVER) && ( $_SERVER["HTTPS"] == "on" )) {
        $pageURL .= "s";
    }
    $pageURL .= "://";
    if ($_SERVER["SERVER_PORT"] != "80") {
        $pageURL .= $_SERVER["SERVER_NAME"] . ":" . $_SERVER["SERVER_PORT"] . $_SERVER["REQUEST_URI"];
    } else {
        $pageURL .= $_SERVER["SERVER_NAME"] . $_SERVER["REQUEST_URI"];
    }
    return $pageURL;
}
if (!function_exists('quality_breadcrumbs')):
 function quality_breadcrumbs() {
 $quality_breadcrumb_type = get_theme_mod('quality_breadcrumb_type','default');
        global $post;
        $homeLink = home_url('/');
        ?>
        <!-- Page Title Section -->
        <section class="page-title-section bg-grey">        
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12 ">
					  <?php 
						if($quality_breadcrumb_type == 'yoast') {
						if ( function_exists('yoast_breadcrumb') ) {
						$wpseo_titles=get_option('wpseo_titles');
						if($wpseo_titles['breadcrumbs-enable']==true){
                         echo '<ul class="page-breadcrumb text-center">';
						$breadcrumbs = yoast_breadcrumb("","",false);
						echo wp_kses_post($breadcrumbs);
						echo '</ul>';
						}	
						}
						echo '<div class="page-title text-center">';
						if (is_home() || is_front_page()) {
						echo '<h1>';
						echo esc_html( single_post_title() );
						echo '</h1>';
						}else {
						quality_archive_page_title();
						}
						echo '</div>';
						}
						elseif($quality_breadcrumb_type == 'navxt'){
						if( function_exists( 'bcn_display' ) )
						{
						echo '<nav class="navxt-breadcrumb text-center">';
						bcn_display();
						echo '</nav>';
						}
						echo '<div class="page-title text-center">';
						if (is_home() || is_front_page()) {
						echo '<h1>';
						echo esc_html( single_post_title() );
						echo '</h1>';
						}else {
						quality_archive_page_title();
						}
						echo '</div>';
						}
						elseif($quality_breadcrumb_type == 'rankmath') {
						if( function_exists( 'rank_math_the_breadcrumbs' ) )
						{
						echo '<ul class="page-breadcrumb text-center">';	
						rank_math_the_breadcrumbs();
						echo '</ul>';
						}
								echo '<div class="page-title text-center">';
								if (is_home() || is_front_page()) {
								echo '<h1>';
								echo esc_html( single_post_title() );
								echo '</h1>';
								}else {
								quality_archive_page_title();
								}
								echo '</div>';
						}
						elseif($quality_breadcrumb_type == 'default') {
                        echo '<ul class="page-breadcrumb text-center">';
                        if (class_exists('WooCommerce')) :
                            if (is_home() || is_front_page()) :
                                echo '<li><a href="' . esc_url($homeLink) . '">' . esc_html__('Home', 'quality') . '</a></li>';
                                echo '<li class="active">';
                                echo esc_html( single_post_title());
                                echo '</li>';
                            elseif(is_woocommerce()):
                                woocommerce_breadcrumb();
                            //echo '<li class="active"><a href="'.$homeLink.'">'.get_bloginfo( 'name' ).'</a></li>';
                            else:
                                echo '<li><a href="' . esc_url($homeLink) . '">' . esc_html__('Home', 'quality') . '</a></li>';
                                // Blog Category
                                if (is_category()) {
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html__('Archive by category', 'quality') . ' "' . single_cat_title('', false) . '"</a></li>';

                                    // Blog Day
                                } elseif (is_day()) {
                                    echo '<li class="active"><a href="' . esc_url(get_year_link(get_the_time('Y'))) . '">' . esc_html(get_the_time('Y')) . '</a>';
                                    echo '<li class="active"><a href="' . esc_url(get_month_link(get_the_time('Y'), get_the_time('m'))) . '">' . esc_html(get_the_time('F')) . '</a>';
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_time('d')) . '</a></li>';

                                    // Blog Month
                                } elseif (is_month()) {
                                    echo '<li class="active"><a href="' . esc_url(get_year_link(get_the_time('Y'))) . '">' . esc_html(get_the_time('Y')) . '</a>';
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_time('F')) . '</a></li>';

                                    // Blog Year
                                } elseif (is_year()) {
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_time('Y')) . '</a></li>';

                                    // Single Post
                                } elseif (is_single() && !is_attachment() && is_page('single-product')) {

                                    // Custom post type
                                    if (get_post_type() != 'post') {
                                        $cat = get_the_category();
                                        $cat = $cat[0];
                                        echo '<li>';
                                        echo get_category_parents($cat, TRUE, '');
                                        echo '</li>';
                                        echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_title()) . '</a></li>';
                                    }
                                } elseif (is_page() && $post->post_parent) {
                                    $parent_id = $post->post_parent;
                                    $breadcrumbs = array();
                                    while ($parent_id) {
                                        $page = get_page($parent_id);
                                        $breadcrumbs[] = '<li class="active"><a href="' . esc_url(get_permalink($page->ID)) . '">' . esc_html(get_the_title($page->ID)) . '</a>';
                                        $parent_id = $page->post_parent;
                                    }
                                    $breadcrumbs = array_reverse($breadcrumbs);
                                    foreach ($breadcrumbs as $crumb)
                                        echo $crumb;

                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_title()) . '</a></li>';
                                } elseif (is_search()) {
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . get_search_query() . '</a></li>';
                                } elseif (is_404()) {
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html__('Error 404', 'quality') . '</a></li>';
                                }
                                else {
                                    // Default
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_title()) . '</a></li>';
                                }
                            endif;

                        echo '</ul>';
                        else:
                        if (is_home() || is_front_page()) :
                                echo '<li><a href="' . esc_url($homeLink) . '">' . esc_html__('Home', 'quality') . '</a></li>';
                                echo '<li class="active">';
                                echo esc_html( single_post_title());
                                echo '</li>';
                        
                            //echo '<li class="active"><a href="'.$homeLink.'">'.get_bloginfo( 'name' ).'</a></li>';
                            else:
                                echo '<li><a href="' . esc_url($homeLink) . '">' . esc_html__('Home', 'quality') . '</a></li>';
                                // Blog Category
                                if (is_category()) {
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html__('Archive by category', 'quality') . ' "' . single_cat_title('', false) . '"</a></li>';

                                    // Blog Day
                                } elseif (is_day()) {
                                    echo '<li class="active"><a href="' . esc_url(get_year_link(get_the_time('Y'))) . '">' . esc_html(get_the_time('Y')) . '</a>';
                                    echo '<li class="active"><a href="' . esc_url(get_month_link(get_the_time('Y'), get_the_time('m'))) . '">' . esc_html(get_the_time('F')) . '</a>';
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_time('d')) . '</a></li>';

                                    // Blog Month
                                } elseif (is_month()) {
                                    echo '<li class="active"><a href="' . esc_url(get_year_link(get_the_time('Y'))) . '">' . esc_html(get_the_time('Y')) . '</a>';
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_time('F')) . '</a></li>';

                                    // Blog Year
                                } elseif (is_year()) {
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_time('Y')) . '</a></li>';

                                    // Single Post
                                } elseif (is_single() && !is_attachment() && is_page('single-product')) {

                                    // Custom post type
                                    if (get_post_type() != 'post') {
                                        $cat = get_the_category();
                                        $cat = $cat[0];
                                        echo '<li>';
                                        echo get_category_parents($cat, TRUE, '');
                                        echo '</li>';
                                        echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_title()) . '</a></li>';
                                    }
                                } elseif (is_page() && $post->post_parent) {
                                    $parent_id = $post->post_parent;
                                    $breadcrumbs = array();
                                    while ($parent_id) {
                                        $page = get_page($parent_id);
                                        $breadcrumbs[] = '<li class="active"><a href="' . esc_url(get_permalink($page->ID)) . '">' . esc_html(get_the_title($page->ID)) . '</a>';
                                        $parent_id = $page->post_parent;
                                    }
                                    $breadcrumbs = array_reverse($breadcrumbs);
                                    foreach ($breadcrumbs as $crumb)
                                        echo $crumb;

                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_title()) . '</a></li>';
                                } elseif (is_search()) {
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . get_search_query() . '</a></li>';
                                } elseif (is_404()) {
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html__('Error 404', 'quality') . '</a></li>';
                                }
                                else {
                                    // Default
                                    echo '<li class="active"><a href="' . esc_url(quality_curPageURL()) . '">' . esc_html(get_the_title()) . '</a></li>';
                                }


                            endif;                     

                        echo '</ul>';
                        endif; ?> 

                        <div class="page-title text-center">
                            <?php
                            if (is_home() || is_front_page()) {
                                echo '<h1>';
                                echo esc_html( single_post_title() );
                                echo '</h1>';
                            }else {
                                quality_archive_page_title();
                            }
                            ?>
                        </div>
						<?php } ?> 
			</div>
			</div>
			</div>  
			</section>
<?php	} endif; ?>