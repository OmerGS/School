<!-- Quality Main Slider --->
<?php
$quality_current_options = wp_parse_args(get_option('quality_pro_options', array()), quality_theme_data_setup());
?>

<!-- /Quality Main Slider --->
<section id="slider-carousel">
    <div id="post-<?php the_ID(); ?>" class="item"
    <?php if ($quality_current_options['home_feature'] != '') { ?>
             style="background-image:url(<?php echo esc_url($quality_current_options['home_feature']); ?>)" <?php } ?>>

        <div class="container">
            <?php if ($quality_current_options['home_image_title'] != '' || $quality_current_options['home_image_sub_title'] != '' || $quality_current_options['home_image_description'] != '' || $quality_current_options['home_image_button_text'] != '') : ?>
                <div class="slider-caption">
                    <?php if ($quality_current_options['home_image_title']) { ?>
                        <h5><?php echo esc_html($quality_current_options['home_image_title']); ?></h5>
                        <?php
                    }
                    if ($quality_current_options['home_image_sub_title']) {
                        ?>
                        <h1><?php echo esc_html($quality_current_options['home_image_sub_title']); ?></h1>
                        <?php
                    }
                    if ($quality_current_options['home_image_description']) {
                        ?>
                        <p><?php echo esc_html($quality_current_options['home_image_description']); ?></p>
                        <?php
                    }
                    if ($quality_current_options['home_image_button_text'] != '') {
                        ?>
                        <div class="slide-btn-area-sm">
                            <a class="slide-btn-sm" href="<?php echo esc_url($quality_current_options['home_image_button_link']); ?>" target="_blank">
                                <?php echo esc_html($quality_current_options['home_image_button_text']); ?>
                            </a>
                        </div>
                    <?php } ?>
                </div>
            <?php endif; ?>
        </div>
    </div>
</section>