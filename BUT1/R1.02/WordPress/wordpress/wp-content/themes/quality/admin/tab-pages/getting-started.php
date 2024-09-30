<?php
/**
 * Getting started template
 */
?>
<div id="getting_started" class="quality-tab-pane active">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">
				<div class="quality-tab-pane-half quality-tab-pane-first-half">
					<h3><?php esc_html_e( "Recommended Plugins", 'quality' ); ?></h3>
					<div style="border-top: 1px solid #eaeaea;">
						<p style="margin-top: 16px;">
							<?php esc_html_e( 'To take full advanctage of the theme features you need to install recommended plugins.', 'quality' ); ?>
						
						</p>
						<p><a target="_self" href="#recommended_actions" class="quality-custom-class"><?php esc_html_e( 'Click here','quality');?></a></p>
					</div>
				</div>
				<div class="quality-tab-pane-half quality-tab-pane-first-half">
					<h3><?php esc_html_e( "Start Customizing", 'quality' ); ?></h3>
					<div style="border-top: 1px solid #eaeaea;">
						<p style="margin-top: 16px;">
							<?php esc_html_e( 'After activating recommended plugins , now you can start customization.', 'quality' ); ?>
						
						</p>
						<p><a target="_blank" href="<?php echo esc_url( admin_url( 'customize.php' ) ); ?>" class="button button-primary"><?php esc_html_e( 'Go to Customizer','quality');?></a></p>
					</div>
				</div>
				<div class="quality-tab-pane-half quality-tab-pane-first-half">
					<h3><?php esc_html_e( "Documentation", 'quality' ); ?></h3>
					<div style="border-top: 1px solid #eaeaea;">
						<p style="margin-top: 16px;">
							<?php esc_html_e( 'Browse the documention for the detailed information regarding this theme.', 'quality' ); ?>
						
						</p>
						<p><a target="_blank" href="<?php echo esc_url('https://help.webriti.com/themes/quality/quality-wordpress-theme/'); ?>"><?php esc_html_e( 'Documentation','quality');?></a></p>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="quality-tab-pane-half quality-tab-pane-first-half">
				<img src="<?php echo esc_url( get_template_directory_uri() ) . '/admin/img/quality.png'; ?>" alt="<?php esc_attr_e( 'Quality Theme', 'quality' ); ?>" />
				</div>
			</div>	
		</div>
		<div class="row">
			<div class="quality-tab-center">
				<h3><?php esc_html_e( "Useful Links", 'quality' ); ?></h3>
			</div>
			<div class=" useful_box">
                <div class="quality-tab-pane-half quality-tab-pane-first-half">
                    <a href="<?php echo esc_url('https://demo.webriti.com/?theme=Quality'); ?>" target="_blank"  class="info-block">
                    	<div class="dashicons dashicons-desktop info-icon"></div>
                    	<p class="info-text"><?php echo esc_html__('Lite Demo','quality'); ?></p>
                	</a>
                    <a href="<?php echo esc_url('https://demo.webriti.com/?theme=Quality%20Pro'); ?>" target="_blank"  class="info-block">
                    	<div class="dashicons dashicons-book-alt info-icon"></div>
                    	<p class="info-text"><?php echo esc_html__('PRO Demo','quality'); ?></p>
                    </a>        
                </div>
                <div class="quality-tab-pane-half quality-tab-pane-first-half">
                    <a href="<?php echo esc_url('https://wordpress.org/support/view/theme-reviews/quality'); ?>" target="_blank"  class="info-block">
                    	<div class="dashicons dashicons-smiley info-icon"></div>
                    	<p class="info-text"><?php echo esc_html__('Your feedback is valuable to us','quality'); ?></p>
                    </a>
                    <a href="<?php echo esc_url('https://webriti.com/quality/'); ?>" target="_blank"  class="info-block">
                    	<div class="dashicons dashicons-book-alt info-icon"></div>
                    	<p class="info-text"><?php echo esc_html__('Premium Theme Details','quality'); ?></p>
                    </a>
                </div>
            </div>        
        </div>            
    </div>
</div>