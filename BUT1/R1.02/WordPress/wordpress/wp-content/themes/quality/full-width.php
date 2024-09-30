<?php
/**
Template Name: Full-width page
*/
get_header();
quality_breadcrumbs();
?>
<div class="clearfix"></div>
<!-- News & Sidebar Section -->
<section id="section-block" class="site-content">
	<div class="container">
		
		<div class="row">	
			<!--Blog Posts-->
			<div class="col-md-12 col-sm-8 col-xs-12">
				<div class="news">
					<article class="post">								
						<div class="post-content">	
							<?php 
							the_post(); 
							the_content(); ?>
						</div>				
					</article>
				</div>
			</div>
			<!--/Blog Posts-->
		</div>			
	</div>
</section>
	<!-- /News & Sidebar Section ---->	
<?php 
get_footer();