//package project.backend.courses.course.service;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import project.backend.courses.course.dto.CourseDTO;
//import project.backend.courses.course.mapper.CourseDTOMapper;
//import project.backend.courses.course.model.Course;
//import project.backend.courses.course.model.CourseState;
//import project.backend.courses.course.repository.CourseRepository;
//import project.backend.courses.language.service.LanguageService;
//import project.backend.courses.lesson.dto.LessonDTO;
//import project.backend.courses.lesson.mapper.LessonDTOMapper;
//import project.backend.courses.lesson.model.Lesson;
//import project.backend.courses.lesson.service.LessonService;
//import project.backend.courses.utils.file.service.FileService;
//import project.backend.exception.types.ResourceNotFoundException;
//import project.backend.permission.service.PermissionService;
//import project.backend.user.User;
//import project.backend.user.UserDTO;
//import project.backend.user.UserService;
//
//import java.math.BigDecimal;
//import java.security.Principal;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class CourseServiceImplTest {
//
//    @Mock
//    private CourseRepository courseRepository;
//
//    @Mock
//    private LanguageService languageService;
//
//    @Mock
//    private CourseDTOMapper courseDTOMapper;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private FileService fileService;
//
//    @Mock
//    private LessonService lessonService;
//
//    @Mock
//    private LessonDTOMapper lessonDTOMapper;
//
//    @Mock
//    private PermissionService permissionService;
//    private CourseService underTest;
//    private final String email = "test@test.com";
//
//    private AutoCloseable autoCloseable;
//
//    private User user;
//    private UserDTO userDTO;
//    private Principal principal;
//
//    @BeforeEach
//    void setUp() {
//        // given
////        User user = User.builder().email(email).build();
////        Course course = Course.builder().author(user).build();
////        userRepository.save(user);
////        underTest.save(course);
//        autoCloseable = MockitoAnnotations.openMocks(this);
//        principal = mock(Principal.class);
//        when(principal.getName()).thenReturn(email);
//        underTest = new CourseServiceImpl(courseRepository, languageService, courseDTOMapper, userService, fileService, lessonService, lessonDTOMapper, permissionService);
//        user = User.builder()
//                .firstName("testName")
//                .lastName("testSurnmae")
//                .email(email)
//                .build();
//        userDTO = new UserDTO(
//                user.getId(),
//                user.getEmail(),
//                user.getFirstName(),
//                user.getLastName()
//        );
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }
//
//    @Test
//    public void Should_TrowsNoResourceFoundException_When_CourseWithGivenIdDoestExist() {
//        // given
//        Long id = 1L;
//
//        // then
//        assertThrows(ResourceNotFoundException.class, () -> underTest.getCourseById(id));
//        verify(courseRepository).findCourseWithSortedLessonsByIdOrderByLessonsLessonNumber(id);
//    }
//
//    @Test
//    public void Should_ReturnCourseDTO_When_CourseWithGivenIdExist() {
//        // given
//        Long id = 1L;
//        Course course = Course.builder().id(id).build();
//
//        // Mock the behavior of courseRepository
//        when(courseRepository.findCourseWithSortedLessonsByIdOrderByLessonsLessonNumber(id)).thenReturn(Optional.of(course));
//
//        // when
//        Course result = underTest.getCourseById(id);
//
//        // then
//        verify(courseRepository).findCourseWithSortedLessonsByIdOrderByLessonsLessonNumber(id);
//        assertEquals(course, result);
//    }
//
////    @Test
////    public void Should_CreateCourse_When_ValidPrincipalIsProvided() {
////        // Given
////        User user = new User();
////        user.setEmail(email);
////
////        Lesson lesson = Lesson.builder()
////                .lessonNumber(1)
////                .title("")
////                .description("")
////                .videoUrl("")
////                .build();
////
////        LessonDTO lessonDTO = new LessonDTO(
////                lesson.getId(),
////                lesson.getTitle(),
////                lesson.getDescription(),
////                "",
////                lesson.getLessonNumber(),
////                lesson.getVideoUrl()
////        );
////
////        Course course = Course.builder()
////                .title("")
////                .description("")
////                .price(new BigDecimal(0))
////                .discountPrice(new BigDecimal(0))
////                .language(null)
////                .categories(null)
////                .rating(0)
////                .targetAudience(null)
////                .courseState(CourseState.CREATING)
////                .enrollmentCount(0)
////                .lessons(List.of(lesson))
////                .author(userService.getUserByEmail(principal.getName()))
////                .build();
////
////        CourseDTO courseDTO = new CourseDTO(
////                course.getId(),
////                course.getTitle(),
////                course.getDescription(),
////                course.getPrice(),
////                course.getDiscountPrice(),
////                null,
////                null,
////                null,
////                course.getRating(),
////                course.getImageUrl(),
////                List.of(lessonDTO),
////                course.getEnrollmentCount(),
////                course.getCourseState(),
////                course.getTargetAudience(),
////                userDTO
////        );
////
////        when(userService.getUserByEmail(principal.getName())).thenReturn(user);
////        when(lessonService.createLesson(any(LessonDTO.class))).thenReturn(lesson);
////        when(courseRepository.save(any(Course.class))).thenReturn(course);
////
////        // When
////        CourseDTO result = underTest.createCourse(courseDTO, principal);
////
////        // Then
////        verify(lessonService).createLesson(any(LessonDTO.class));
////        verify(courseRepository).save(any(Course.class));
////        assertNotNull(result);
////    }
//
//    @Test
//    void shouldDeleteCourseImage() {
//        // given
//        Long courseId = 1L;
//        Course course = Course.builder().id(courseId).imageUrl("image_url").build();
//        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
//
//        // when
//        underTest.deleteCourseImage(courseId);
//
//        // then
//        verify(fileService).deleteFile("image_url");
//        assertNull(course.getImageUrl());
//        verify(courseRepository).save(course);
//    }
//
//    @Test
//    void shouldReturnSignedUrlForImageUpload() {
//        // given
//        Long courseId = 1L;
//        Course course = Course.builder().id(courseId).build();
//        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
//        String fileName = "public/courses/" + courseId + "/" + UUID.randomUUID().toString();
//        String expectedUrl = "http://aws-s3-url/" + fileName;
//        when(fileService.generateUploadUrl(any(), any())).thenReturn("signed_url");
//
//        // when
//        String result = underTest.getSignedUrlForImageUpload(courseId);
//
//        // then
//        assertNotNull(course.getImageUrl());
//        verify(courseRepository).save(course);
//        assertEquals("signed_url", result);
//    }
//
//    @Test
//    void shouldThrowResourceNotFoundExceptionWhenGettingSignedUrlIfCourseDoesNotExist() {
//        // given
//        Long courseId = 1L;
//        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());
//
//        // then
//        assertThrows(ResourceNotFoundException.class, () -> underTest.getSignedUrlForImageUpload(courseId));
//        verify(fileService, never()).generateUploadUrl(any(), any());
//        verify(courseRepository, never()).save(any(Course.class));
//    }
//
//    @Test
//    void shouldDeleteCourse() {
//        // given
//        Long courseId = 1L;
//        Course course = Course.builder().id(courseId).courseState(CourseState.PUBLISHED).build();
//        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
//
//        // when
//        underTest.deleteCourse(courseId);
//
//        // then
//        assertEquals(CourseState.HIDDEN, course.getCourseState());
//        verify(courseRepository).save(course);
//    }
//}