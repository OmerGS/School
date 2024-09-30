<?php
  get_header(); 
  quality_breadcrumbs(); ?>
<section id="section-block" class="error-page">		
	<div class="container">
		<div class="row v-center">
			<div class="col-md-12 col-sm-12 col-xs-12">							
				<div class="error-404 text-center">
					<h1><?php esc_html_e('4','quality'); ?><i class="fa fa-frown-o"></i><?php esc_html_e('4','quality'); ?> </h1>
                                        <h2><?php esc_html_e('Oops! Page not found','quality'); ?></h2>
					<p><?php esc_html_e('We are sorry, but the page you are looking for does not exist.','quality'); ?></p>
					<div class="btn-block text-center">
						<a href="<?php echo esc_url(home_url('/'));?>" class="btn-large"><i class="fa fa-hand-o-left text-white"></i><?php esc_html_e('Back to Homepage','quality'); ?></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- 404 Error Section -->
<?php get_footer();