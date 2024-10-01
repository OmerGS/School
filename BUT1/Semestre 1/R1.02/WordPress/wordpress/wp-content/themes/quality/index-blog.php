<section id="section-block" class="news">
    <div class="container">
        <?php
        $quality_current_options = wp_parse_args(get_option('quality_pro_options', array()), quality_theme_data_setup());
        if (($quality_current_options['blog_heading']) || ($quality_current_options['home_blog_description'] != '' )) {
            ?>
            <div class="row">
                <div class="section-header">			
                    <?php if ($quality_current_options['blog_heading']) { ?>
                        <p><?php echo wp_kses_post( $quality_current_options['blog_heading'] ); ?></p>
                    <?php } if ($quality_current_options['home_blog_description']) { ?>
                        <h1 class="widget-title"><?php echo  wp_kses_post($quality_current_options['home_blog_description']) ; ?></h1>
                    <?php } ?>
                    <hr class="divider">
                </div>
            </div>
        <?php } ?>
        <div class="row">
            <?php
            $quality_args = array('post_type' => 'post', 'posts_per_page' => 2, 'post__not_in' => get_option("sticky_posts"));
            query_posts($quality_args);
            if (query_posts($quality_args)) {
                while (have_posts()):the_post(); {
                        $quality_recent_expet = get_the_excerpt();
                        ?>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <article class="post">								
                                <?php
                                $quality_defalt_arg = array('class' => "img-responsive");
                                if (has_post_thumbnail()):
                                    ?>
                                    <figure class="post-thumbnail">
                                        <?php the_post_thumbnail('', $quality_defalt_arg); ?>
                                    </figure>
                                <?php endif; ?>	

                                <div class="post-content">	
                                    <?php if ($quality_current_options['home_meta_section_settings'] == '' || $quality_current_options['home_meta_section_settings'] == false) { ?>
                                        <div class="item-meta">
                                            <a class="author-image item-image" href="<?php echo esc_url(get_author_posts_url(get_the_author_meta('ID'))); ?>"><?php echo get_avatar(get_the_author_meta('user_email'), $quality_size = '40'); ?></a>
                                            <?php echo ' '; ?><a href="<?php echo esc_url(get_author_posts_url(get_the_author_meta('ID'))); ?>"><?php echo esc_html(get_the_author()); ?></a>
                                            <br>
                                            <a class="entry-date" href="<?php echo esc_url(get_month_link(get_post_time('Y'), get_post_time('m'))); ?>">
                                                <?php echo esc_html(get_the_date()); ?></a>
                                        </div>
                                    <?php } ?>
                                    <header class="entry-header">
                                        <h2 class="entry-title"><a href="<?php the_permalink(); ?>"><?php the_title(); ?></a></h2>
                                    </header>	
                                    <div class="entry-content">
                                        <?php the_content(__('Read More', 'quality')); ?>
                                    </div>
                                    <?php if ($quality_current_options['home_meta_section_settings'] == ''  || $quality_current_options['home_meta_section_settings'] == false) { ?>
                                        <hr />
                                        <div class="entry-meta">
                                            <span class="comment-links"><?php comments_popup_link( esc_html__('Leave a comment', 'quality' ) ); ?></span>
                                            <?php
                                            $quality_cat_list = get_the_category_list();
                                            if (!empty($quality_cat_list)) {
                                                ?>
                                                <span class="cat-links"><?php esc_html_e('In', 'quality'); ?><?php the_category(' '); ?></span>
                                            <?php } ?>

                                        </div>
                                    <?php } ?>
                                </div>				
                            </article>
                        </div>
                        <?php
                    } endwhile;
            }
            ?>
        </div>
    </div>
    <!-- /Quality Blog Section ---->
</section>
